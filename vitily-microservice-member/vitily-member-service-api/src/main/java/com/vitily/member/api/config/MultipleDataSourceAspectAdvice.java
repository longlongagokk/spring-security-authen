package com.vitily.member.api.config;

import com.vitily.jdbc.MultipleDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {
    private final static Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspectAdvice.class);
    public final static String COM_SERVICE_EXECU_PATTERN = "execution(* com.vitily.member.server.service.impl.*.*(..))";
    public final static String TEST_SERVICE_EXECU_PATTERN = "execution(* com.vitily.service.impl.*.*(..))";
    @Around(COM_SERVICE_EXECU_PATTERN)
    public Object comAround(ProceedingJoinPoint jp) throws Throwable {
        logger.debug("--------use datasource0----------");
        MultipleDataSource.setDataSourceKey("datasource0");
        return jp.proceed();
    }

    @Around(TEST_SERVICE_EXECU_PATTERN)
    public Object testAround(ProceedingJoinPoint jp) throws Throwable {
        logger.debug("--------use datasource1----------");
        MultipleDataSource.setDataSourceKey("datasource1");
        return jp.proceed();
    }
}
