package com.github.jjYBdx4IL.test.model.jpa.uniquecompidx;

/*
 * #%L
 * Spring Hibernate Evaluation
 * %%
 * Copyright (C) 2015 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import com.github.jjYBdx4IL.test.model.jpa.common.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
@Entity
@SuppressWarnings("serial")
@Table(indexes={@Index(columnList="user,name", unique=true)})
public class UniqueCompositeIndexEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "user")
    private User user;
    @Basic
    private String name;
    @Basic
    private String description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    @Version
    private Long version;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return new Date(createdAt.getTime());
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = new Date(createdAt.getTime());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UniqueCompositeIndexEntity [");
        builder.append("createdAt=");
        builder.append(createdAt);
        builder.append(", description=");
        builder.append(description);
        builder.append(", id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", user=");
        builder.append(user);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }

}

