/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa.common;

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
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String name;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [");
        builder.append("id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}
