package org.demolee.tx;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Audited(useCase = "")
@Priority(2000)
public class Auditor {

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    RequestBody bodyProducer;

    @AroundInvoke
    public Object wrap(InvocationContext ic) throws Exception {
        var name = ic.getMethod().getName();
        var params = ic.getParameters();
        var useCase = ic.getMethod().getAnnotation(Audited.class).useCase();
        transactionRepository.logTransaction(new Transaction(
            useCase,
            name,
            bodyProducer.getRaw(),
            params));
        return ic.proceed();
    }

}
