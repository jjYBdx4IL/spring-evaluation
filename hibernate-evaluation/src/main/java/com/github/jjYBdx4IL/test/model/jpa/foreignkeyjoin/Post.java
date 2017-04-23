package com.github.jjYBdx4IL.test.model.jpa.foreignkeyjoin;

/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private String title;
    @Basic
    private String content;
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    public UserFKJoin user;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Post [");
        builder.append("content=");
        builder.append(content);
        builder.append(", id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }


}
