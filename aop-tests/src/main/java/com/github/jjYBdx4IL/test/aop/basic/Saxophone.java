package com.github.jjYBdx4IL.test.aop.basic;

import org.springframework.stereotype.Component;

@Component
public class Saxophone implements Instrument {

    public Saxophone() {
    }

    @Override
    public void play() {
        TestHelper.msgs.add("TOOT TOOT TOOT");
    }
}
