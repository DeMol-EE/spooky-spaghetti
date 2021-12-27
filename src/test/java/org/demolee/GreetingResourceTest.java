package org.demolee;

import java.util.Map;

import org.demolee.tx.Transaction;
import org.demolee.tx.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
public class GreetingResourceTest {

    @InjectMock
    TransactionRepository transactionRepository;

    @Test
    public void auditor() {
        RestAssured
                .given()
                .contentType("application/json")
                .body(Map.of("hello", "world"))
                .post()
                .then()
                .log()
                .ifValidationFails();
        var transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        Mockito.verify(transactionRepository)
                .logTransaction(transactionCaptor.capture());
        Transaction transaction = transactionCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals("SAY_HI", transaction.getUseCase()),
                () -> Assertions.assertEquals("sayHello", transaction.getName()),
                () -> Assertions.assertEquals("{\"hello\":\"world\"}", transaction.getRawBody()));
    }
}
