package com.github.jjYBdx4IL.test.model.jpa.misc;

/*
 * #%L
 * Shared Package
 * %%
 * Copyright (C) 2014 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

// CHECKSTYLE IGNORE HiddenField FOR NEXT 1000 LINES
@SuppressWarnings("serial")
@Entity
@Indexed
public class BlogWithFullTextIndex implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String subject;
    private String body;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    @Version
    private Long version;

    @Field
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Field
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Field
    public Date getCreatedAt() {
        return createdAt == null ? null : new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BlogWithFullTextIndex [");
        builder.append("body=");
        builder.append(body);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", id=");
        builder.append(id);
        builder.append(", subject=");
        builder.append(subject);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }
}
