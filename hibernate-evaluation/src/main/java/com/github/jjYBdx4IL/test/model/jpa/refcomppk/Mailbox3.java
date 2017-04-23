/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa.refcomppk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author Github jjYBdx4IL Projects
 */
@SuppressWarnings("serial")
@Entity
public class Mailbox3 implements Serializable {

    public Mailbox3() {
    }

    @Id
    @Basic
    private String id;
    @Id
    @Basic
    private String uid;
    @Basic
    private String desc;
    @Version
    private int version;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mailbox3 [");
        builder.append("desc=");
        builder.append(desc);
        builder.append(", id=");
        builder.append(id);
        builder.append(", uid=");
        builder.append(uid);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

    
}
