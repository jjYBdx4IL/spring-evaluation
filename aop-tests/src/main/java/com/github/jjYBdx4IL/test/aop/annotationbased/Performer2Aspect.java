package com.github.jjYBdx4IL.test.aop.annotationbased;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Performer2Aspect {

    public Performer2Aspect() {
    }
    
    @Pointcut("execution(@Performer2Annotation * *.*(..))")
    private void pointcut() {}
    
    @Around("pointcut()")
    public Object aroundPerform2(ProceedingJoinPoint pjp) throws Throwable {
        Performer2.testValue++;
        Object o = pjp.proceed();
        Performer2.testValue += 2;
        return o;
    }
}
