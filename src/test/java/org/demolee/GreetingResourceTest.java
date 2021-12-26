package org.demolee;

import org.demolee.tx.TransactionRepository;
import org.junit.jupiter.api.Test;
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
                .queryParam("name", "Werner")
                .get();
        Mockito.verify(transactionRepository)
                .logTransaction("sayHello", new Object[] { "Werner" });
    }
}
