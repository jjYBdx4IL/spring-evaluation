package com.github.jjYBdx4IL.test.aop.classannotationwitharg;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Performer5Aspect {

    public Performer5Aspect() {
    }
    
    @Around(value = "execution(public * *(..)) && within(@Performer5Annotation *)")
    public Object aroundPerform5(ProceedingJoinPoint pjp) throws Throwable {
        Performer5.testValue += pjp.getTarget().getClass().getAnnotation(Performer5Annotation.class).value();
        Object o = pjp.proceed();
        Performer5.testValue += 4;
        return o;
    }
}
