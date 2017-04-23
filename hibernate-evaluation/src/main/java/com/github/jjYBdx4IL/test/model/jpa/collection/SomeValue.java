/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa.collection;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * 
 * @author Github jjYBdx4IL Projects
 */
@SuppressWarnings("serial")
@Entity
public class SomeValue implements Serializable {

    public SomeValue() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private Double value;
    @Version
    private int version;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SomeValue [");
        builder.append("id=");
        builder.append(id);
        builder.append(", value=");
        builder.append(value);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}
