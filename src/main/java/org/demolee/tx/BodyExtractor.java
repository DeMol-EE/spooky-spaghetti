package org.demolee.tx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class BodyExtractor implements ContainerRequestFilter {

    @Inject
    RequestBody requestBody;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        InputStream in = requestContext.getEntityStream();
        byte[] requestEntity = in.readAllBytes();
        requestBody.setRaw(new String(requestEntity));
        requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    }

}
