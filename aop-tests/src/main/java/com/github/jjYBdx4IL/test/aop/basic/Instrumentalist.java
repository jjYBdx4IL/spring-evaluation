package com.github.jjYBdx4IL.test.aop.basic;

import org.springframework.beans.factory.annotation.Autowired;

public class Instrumentalist implements Performer {

    public Instrumentalist() {
    }

    @Override
    public void perform() throws PerformanceException {
        TestHelper.msgs.add("Playing " + song + " : ");
        instrument.play();
    }
    private String song;

    public void setSong(String song) {
        this.song = song;
    }
    
    @Autowired
    private Instrument instrument;

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
