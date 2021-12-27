package org.demolee.tx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TransactionRepository {

    @Inject
    Jsonb jsonb;

    private final Logger log = LoggerFactory.getLogger(TransactionRepository.class);

    public void logTransaction(Transaction transaction) {
        String json = jsonb.toJson(transaction);
        log.info(json);
    }
    
}
