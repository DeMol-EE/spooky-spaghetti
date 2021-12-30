package org.demolee;

import java.util.Map;

import org.demolee.tx.Transaction;
import org.demolee.tx.TransactionRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
public class GreetingResourceTest {

    @InjectMock
    TransactionRepository transactionRepository;

    @Test
    public void auditor() {
        RequestSpecification request = RestAssured.given()
                .contentType("application/json")
                .body(Map.of("hello", "world"));
        Response response = request.when().post();
        response.then().log().ifValidationFails()
                .and().body("hello", Matchers.is("world"));
        Mockito.verify(transactionRepository)
                .logTransaction(new Transaction(
                        "SAY_HI", 
                        "sayHello",
                        "{\"hello\":\"world\"}"));
    }
}
