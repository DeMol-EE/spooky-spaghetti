package org.demolee;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.demolee.tx.Audited;

@Path("greeting")
public class GreetingResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Audited(useCase = "SAY_HI")
    public Object sayHello(Object body) {
        return body;
    }
}
