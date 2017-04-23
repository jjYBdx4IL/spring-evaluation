package com.github.jjYBdx4IL.test.aop.classandmethodanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Performer6Aspect {

    public Performer6Aspect() {
    }
    
    @Pointcut("execution(public * *(..)) && within(@Performer6Annotation *) || execution(@Performer6Annotation public * *.*(..))")
    public void pointcut() {};
    
    @Around("pointcut()")
    public Object aroundPerform6(ProceedingJoinPoint pjp) throws Throwable {
        Performer6.testValue += 2;
        Performer6b.testValue += 2;
        Performer6c.testValue += 2;
        Object o = pjp.proceed();
        Performer6.testValue += 3;
        Performer6b.testValue += 3;
        Performer6c.testValue += 3;
        return o;
    }
}
