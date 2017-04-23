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
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class DataSetWithValueList implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<SomeValue> values = new ArrayList<>();
    @Basic
    private String desc;
    @Version
    private int version;

    /**
     * @return the values
     */
    public Collection<SomeValue> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(Collection<SomeValue> values) {
        this.values = values;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
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
        builder.append("DataSetWithValueList [");
        builder.append("desc=");
        builder.append(desc);
        builder.append(", id=");
        builder.append(id);
        builder.append(", values=");
        builder.append(values);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}
