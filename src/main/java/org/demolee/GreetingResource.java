package org.demolee;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.demolee.tx.Audited;

@Path("greeting")
public class GreetingResource {

    @GET
    @Audited(useCase = "SAY_HI")
    public String sayHello(
            @QueryParam("name") @DefaultValue("World") String name) {
        return "Hello " + name;
    }
}
