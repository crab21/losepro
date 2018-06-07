package com.example.combinerabbit.config.mysql;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {
    @Before(value = "@annotation(TargetSource)")
    public void before(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
//        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        String datasource = DynamicDataSourceHolder.DEFAULTS;
        try {
            Method method = aClass.getMethod(name, parameterTypes);
            if (method != null && method.isAnnotationPresent(TargetSource.class)) {
                TargetSource annotation = method.getAnnotation(TargetSource.class);
                datasource = annotation.value();


            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DynamicDataSourceHolder.putDataSource(datasource);
    }


    @After(value = "@annotation(TargetSource)")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.removeDataSource();
    }
}
