package org.demolee.tx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class BodyExtractor implements ContainerRequestFilter {

    @Inject
    RequestBody bodyProducer;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        byte[] requestEntity = out.toByteArray();
        bodyProducer.setRaw(new String(requestEntity));
        requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    }

}
