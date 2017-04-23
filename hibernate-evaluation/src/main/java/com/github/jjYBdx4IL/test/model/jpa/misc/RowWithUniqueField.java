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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

// CHECKSTYLE IGNORE HiddenField FOR NEXT 1000 LINES
@SuppressWarnings("serial")
@Entity
public class RowWithUniqueField implements Serializable {

    private Long id;
    private String uniqueField;
    private Long version;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * @return the uniqueField
     */
    @Column(unique = true)
    public String getUniqueField() {
        return uniqueField;
    }

    /**
     * @param uniqueField the uniqueField to set
     */
    public void setUniqueField(String uniqueField) {
        this.uniqueField = uniqueField;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RowWithUniqueField [");
        builder.append("id=");
        builder.append(id);
        builder.append(", uniqueField=");
        builder.append(uniqueField);
        builder.append(", version=");
        builder.append(version);
        builder.append("]");
        return builder.toString();
    }
}
