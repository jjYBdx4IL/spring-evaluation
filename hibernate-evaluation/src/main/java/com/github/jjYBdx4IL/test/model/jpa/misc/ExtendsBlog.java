package com.github.jjYBdx4IL.test.model.jpa.misc;

/*
 * #%L
 * Shared Package
 * %%
 * Copyright (C) 2014 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import javax.persistence.Entity;
import javax.persistence.Lob;

// CHECKSTYLE IGNORE HiddenField FOR NEXT 1000 LINES
@SuppressWarnings("serial")
@Entity
public class ExtendsBlog extends Blog {

    private String sender;
    private byte[] rawData;

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

    /**
     * @return the rawData
     */
    @Lob
    public byte[] getRawData() {
        return rawData == null ? null : rawData.clone();
    }

    /**
     * @param rawData the rawData to set
     */
    public void setRawData(byte[] rawData) {
        this.rawData = rawData == null ? null : rawData.clone();
    }
}
