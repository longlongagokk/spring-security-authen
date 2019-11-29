package com.vitily.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
public class DataSourceFac {

    private static final int TX_METHOD_TIMEOUT = 5;
    public static TransactionInterceptor getTxAdvice(PlatformTransactionManager transactionManager,
                                                     String rollBackTransactionAttribute,
                                                     String readOnlyTransactionAttribute) {
        log.info("---------txAdvice----------");
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        String dot = ".";
        String[] rollBackAttrs = rollBackTransactionAttribute.split(",");
        for(String s:rollBackAttrs){
            txMap.put(s, requiredTx);
        }
        String[] readOnlyAttrs = readOnlyTransactionAttribute.split(",");
        for(String s:readOnlyAttrs){
            txMap.put(s, readOnlyTx);
        }
        source.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager, source);
    }
    public static DefaultPointcutAdvisor testAdviceAdvisor(String pattern,TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(pattern);
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }
}
