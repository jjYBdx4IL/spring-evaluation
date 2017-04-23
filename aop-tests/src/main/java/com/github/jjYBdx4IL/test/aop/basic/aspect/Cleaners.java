package com.github.jjYBdx4IL.test.aop.basic.aspect;

import com.github.jjYBdx4IL.test.aop.basic.TestHelper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Cleaners {

    public Cleaners() {
    }

    @Around("execution(* *.perform(..))")
    public Object aroundPerform(ProceedingJoinPoint pjp) throws Throwable {
        Object o = pjp.proceed();
        TestHelper.msgs.add("Can do without pointcut ref!");
        return o;
    }
}
