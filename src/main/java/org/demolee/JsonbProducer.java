package org.demolee;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyVisibilityStrategy;

public class JsonbProducer {

    @ApplicationScoped
    public Jsonb jsonb() {
        var config = new JsonbConfig()
                .withPropertyVisibilityStrategy(new RecordFieldExposer());
        return JsonbBuilder.create(config);
    }
    
    private class RecordFieldExposer implements PropertyVisibilityStrategy {

        @Override
        public boolean isVisible(Field field) {
            return true;
        }

        @Override
        public boolean isVisible(Method method) {
            return false;
        }

    }
}
