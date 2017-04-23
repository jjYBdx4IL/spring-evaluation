/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk2;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Message2 implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    public Mailbox2 mailbox;
    @Id
    private long uid;
    @Basic
    public String title;
    @Basic
    public String content;
    @Basic
    public String fromAddr;
    @Basic
    public String toAddrs;
    @Version
    private int version;

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message2 [");
        builder.append("content=");
        builder.append(content);
        builder.append(", fromAddr=");
        builder.append(fromAddr);
        builder.append(", mailbox=");
//        builder.append(mailbox);
        builder.append(", title=");
        builder.append(title);
        builder.append(", toAddrs=");
        builder.append(toAddrs);
        builder.append(", uid=");
        builder.append(uid);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}
