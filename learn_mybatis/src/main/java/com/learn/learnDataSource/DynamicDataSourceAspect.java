package com.learn.learnDataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by k on 2018/6/7.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(DS)")
    public void beforeSwitchDb(JoinPoint joinPoint) {
        Class<?> aClass = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULTS;
        try {
            Method method = aClass.getMethod(name, parameterTypes);
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                dataSource = annotation.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDb(dataSource);
    }

    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point) {

        DataSourceContextHolder.removeDb();

    }
}
