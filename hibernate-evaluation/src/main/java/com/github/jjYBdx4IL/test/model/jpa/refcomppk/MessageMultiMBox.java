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
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class MessageMultiMBox implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private Collection<Mailbox3> mailbox = new ArrayList<>();
    @Basic
    private String title;
    @Version
    private int version;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the mailbox
     */
    public Collection<Mailbox3> getMailbox() {
        return mailbox;
    }

    /**
     * @param mailbox the mailbox to set
     */
    public void setMailbox(Collection<Mailbox3> mailbox) {
        this.mailbox.addAll(mailbox);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message3 [");
        builder.append("id=");
        builder.append(id);
        builder.append(", mailbox=");
        builder.append(mailbox);
        builder.append(", title=");
        builder.append(title);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }
}
