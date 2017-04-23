package com.github.jjYBdx4IL.test.model.jpa.enumeration;

/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2015 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EntityWithEnum {

    @Id
    @GeneratedValue
    public long id;
    @Basic
    public SomeEnum someEnum;

}
