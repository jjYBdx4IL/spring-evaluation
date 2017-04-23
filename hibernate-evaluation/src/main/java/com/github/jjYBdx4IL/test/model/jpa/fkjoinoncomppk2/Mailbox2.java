package com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk2;

/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2015 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author Github jjYBdx4IL Projects
 */
@Entity
public class Mailbox2 {

    public Mailbox2() {
        messages = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String title;
    @Basic
    private String desc;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="mailbox")
    public List<Message2> messages;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        builder.append("Mailbox2 [");
        builder.append("desc=");
        builder.append(desc);
        builder.append(", id=");
        builder.append(id);
        builder.append(", messages=");
        //builder.append(messages);
        builder.append(", title=");
        builder.append(title);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}
