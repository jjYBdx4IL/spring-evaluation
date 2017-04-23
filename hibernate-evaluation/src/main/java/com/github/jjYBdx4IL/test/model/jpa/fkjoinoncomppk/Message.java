package com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk;

/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 Github jjYBdx4IL Projects
 * %%
 * #L%
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@IdClass(Message.MailboxUidKey.class)
@Entity
public class Message {

    public static class MailboxUidKey implements Serializable {

        private static final long serialVersionUID = 7847632032426660996L;

        public MailboxUidKey() {}

        /** The value for the mailbox field */
        public long mailbox;

        /** The value for the uid field */
        public long uid;

        @Override
        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + (int) (mailbox ^ (mailbox >>> 32));
            result = PRIME * result + (int) (uid ^ (uid >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final MailboxUidKey other = (MailboxUidKey) obj;
            if (mailbox != other.mailbox)
                return false;
            if (uid != other.uid)
                return false;
            return true;
        }
    }

    @Id
    @ManyToOne(
            cascade = {
                CascadeType.PERSIST,
                CascadeType.REFRESH,
                CascadeType.MERGE},
            fetch = FetchType.LAZY)
    //@JoinColumn(name = "id")
    public Mailbox mailbox;
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
        builder.append("Message [");
        builder.append("content=");
        builder.append(content);
        builder.append(", fromAddr=");
        builder.append(fromAddr);
        builder.append(", mailbox=");
        builder.append(mailbox);
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
