package org.demolee.tx;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestBody {
    
    private String raw;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String body) {
        this.raw = body;
    }
}
