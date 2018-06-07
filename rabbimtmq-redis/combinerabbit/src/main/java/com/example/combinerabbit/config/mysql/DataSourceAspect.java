package com.example.combinerabbit.config.mysql;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {
    @Pointcut(value = "execution(* com.example.combinerabbit.mapper..*.*(..))")
    public void dataSourcePointCut() {

    }

    @Before(value = "dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String name = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        System.out.println(clazz.length + ":::::::::::::::::::::::::::::::::");
        try {
            Method method = clazz[0].getMethod(name, parameterTypes);
            if (method != null && method.isAnnotationPresent(TargetSource.class)) {
                TargetSource annotation = method.getAnnotation(TargetSource.class);
                String value = annotation.value();
                System.out.println(value + ":::::::::::::::::::::::::::");
                DynamicDataSourceHolder.putDataSource(value);
                System.out.println(value + "++++++++++++++++++++++");

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @After(value = "dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.removeDataSource();
    }
}
