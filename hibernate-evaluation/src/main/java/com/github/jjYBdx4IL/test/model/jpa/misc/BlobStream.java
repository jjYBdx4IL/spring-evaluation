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
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.Version;

// CHECKSTYLE IGNORE HiddenField FOR NEXT 1000 LINES
@SuppressWarnings("serial")
@Entity
public class BlobStream implements Serializable {

    private Long id;
    private String subject;
    private Blob body;
    private Date createdAt;
    private Long version;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Lob
    public Blob getBody() {
        return body;
    }

    public void setBody(Blob body) {
        this.body = body;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt == null ? null : new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }

    /**
     * @return the version
     */
    @Version
    public Long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Blog [");
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
