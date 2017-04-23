package com.github.jjYBdx4IL.test.model.jpa.misc;

/*
 * #%L
 * Shared Package
 * %%
 * Copyright (C) 2014 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

// CHECKSTYLE IGNORE HiddenField FOR NEXT 1000 LINES
@SuppressWarnings("serial")
public class ExtendsBlogWithoutEntityAnnotation extends Blog {

    private String sender;

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}
