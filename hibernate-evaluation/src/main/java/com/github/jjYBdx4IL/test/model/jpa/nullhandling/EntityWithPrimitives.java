/*
 * #%L
 * Spring Hibernate Evaluation
 * %%
 * Copyright (C) 2015 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa.nullhandling;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
@SuppressWarnings("serial")
@Entity
public class EntityWithPrimitives implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String text;
    @Basic
    private Long longObject;
    @Basic
    private Boolean booleanObject;
    @Basic
    private long longPrimitive;
    @Basic
    private boolean booleanPrimitive;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the longObject
     */
    public Long getLongObject() {
        return longObject;
    }

    /**
     * @param longObject the longObject to set
     */
    public void setLongObject(Long longObject) {
        this.longObject = longObject;
    }

    /**
     * @return the booleanObject
     */
    public Boolean getBooleanObject() {
        return booleanObject;
    }

    /**
     * @param booleanObject the booleanObject to set
     */
    public void setBooleanObject(Boolean booleanObject) {
        this.booleanObject = booleanObject;
    }

    /**
     * @return the longPrimitive
     */
    public long getLongPrimitive() {
        return longPrimitive;
    }

    /**
     * @param longPrimitive the longPrimitive to set
     */
    public void setLongPrimitive(long longPrimitive) {
        this.longPrimitive = longPrimitive;
    }

    /**
     * @return the booleanPrimitive
     */
    public boolean getBooleanPrimitive() {
        return booleanPrimitive;
    }

    /**
     * @param booleanPrimitive the booleanPrimitive to set
     */
    public void setBooleanPrimitive(boolean booleanPrimitive) {
        this.booleanPrimitive = booleanPrimitive;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EntityWithPrimitives [");
        builder.append("booleanObject=");
        builder.append(booleanObject);
        builder.append(", booleanPrimitive=");
        builder.append(booleanPrimitive);
        builder.append(", id=");
        builder.append(id);
        builder.append(", longObject=");
        builder.append(longObject);
        builder.append(", longPrimitive=");
        builder.append(longPrimitive);
        builder.append(", text=");
        builder.append(text);
        builder.append("]");
        return builder.toString();
    }

}
