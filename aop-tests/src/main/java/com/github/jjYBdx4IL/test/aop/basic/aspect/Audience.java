package com.github.jjYBdx4IL.test.aop.basic.aspect;

import com.github.jjYBdx4IL.test.aop.basic.TestHelper;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Audience {

    public Audience() {
    }

    @Pointcut("execution(* *.perform(..))")
    public void performance() {
        TestHelper.msgs.add("Inside aspect performance method.");
    }

    @Before("performance()")
    public void takeSeats() {
        TestHelper.msgs.add("The audience is taking their seats.");
    }

    @Before("performance()")
    public void turnOffCellPhones() {
        TestHelper.msgs.add("The audience is turning off their cellphones");
    }

    @AfterReturning("performance()")
    public void applaud() {
        TestHelper.msgs.add("CLAP CLAP CLAP CLAP CLAP");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        TestHelper.msgs.add("Boo! We want our money back!");
    }
}
