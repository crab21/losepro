package com.example.combinerabbit.config.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class AopTest implements MethodInterceptor {
    @Pointcut(value = "execution(* com.example.combinerabbit.Controller..*.*(..))")
    public void names() {
    }

    @Before("names()")
    public void prin() {
        System.out.println("----------------before");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start(); // 计时开始
        Object result = invocation.proceed();
        clock.stop(); // 计时结束

        // 方法参数类型，转换成简单类型
        Class[] params = invocation.getMethod().getParameterTypes();
        String[] simpleParams = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            simpleParams[i] = params[i].getSimpleName();
        }
        Object[] args = invocation.getArguments();

        System.out.println("Takes:" + clock.getTotalTimeMillis() + " ms ["
                + invocation.getThis().getClass().getName() + "."
                + invocation.getMethod().getName() + "("
                + StringUtils.join(simpleParams, ",") + ")("
                + StringUtils.join(args, ",") + ")] ");
        return result;
    }
}
