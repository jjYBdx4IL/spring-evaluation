package com.github.jjYBdx4IL.test.aop.annotationwitharg;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Performer4Aspect {

    public Performer4Aspect() {
    }
    
    @Around("execution(@Performer4Annotation(11) * *.*(..)) && @annotation(performer4annotation)")
    public Object aroundPerform4(ProceedingJoinPoint pjp, Performer4Annotation performer4annotation) throws Throwable {
        Performer4.testValue += performer4annotation.value();
        Object o = pjp.proceed();
        Performer4.testValue += 6;
        return o;
    }
}
