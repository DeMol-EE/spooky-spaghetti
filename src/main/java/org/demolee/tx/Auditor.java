package org.demolee.tx;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Audited(useCase = "")
public class Auditor {

    @Inject
    TransactionRepository transactionRepository;

    @AroundInvoke
    public Object wrap(InvocationContext ic) throws Exception {
        var name = ic.getMethod().getName();
        var params = ic.getParameters();
        transactionRepository.logTransaction(name, params);
        return ic.proceed();
    }

}
