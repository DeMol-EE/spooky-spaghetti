package org.demolee.tx;

import java.time.ZonedDateTime;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;

@ApplicationScoped
public class TransactionRepository {

    @Inject
    Jsonb jsonb;

    public void logTransaction(
        String method,
        Object[] parameters
    ) {
        String json = jsonb.toJson(Map.of(
                "name", method,
                "params", parameters,
                "now", ZonedDateTime.now().toInstant()));
        System.out.println(json);
    }
    
}
