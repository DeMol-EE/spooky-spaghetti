package org.demolee.tx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@Audited(useCase = "")
public class TransactionAuditor implements ContainerRequestFilter {

    @Inject
    TransactionRepository transactionRepository;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        InputStream in = requestContext.getEntityStream();
        byte[] requestEntity = in.readAllBytes();
        var method = resourceInfo.getResourceMethod();
        var annotation = method.getAnnotation(Audited.class);
        transactionRepository.logTransaction(new Transaction(
            annotation.useCase(),
            method.getName(), 
            new String(requestEntity)));
        requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
    }

}
