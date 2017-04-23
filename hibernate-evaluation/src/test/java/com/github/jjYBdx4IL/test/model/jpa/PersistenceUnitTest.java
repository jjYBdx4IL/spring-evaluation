/*
 * #%L
 * Spring-Hibernate Evaluation
 * %%
 * Copyright (C) 2014 - 2016 Github jjYBdx4IL Projects
 * %%
 * #L%
 */
package com.github.jjYBdx4IL.test.model.jpa;

import com.github.jjYBdx4IL.test.model.jpa.collection.DataSetWithValueList;
import com.github.jjYBdx4IL.test.model.jpa.collection.SomeValue;
import com.github.jjYBdx4IL.test.model.jpa.common.PrioritizedUser;
import com.github.jjYBdx4IL.test.model.jpa.common.PrioritizedUser_;
import com.github.jjYBdx4IL.test.model.jpa.common.User;
import com.github.jjYBdx4IL.test.model.jpa.enumeration.EntityWithEnum;
import com.github.jjYBdx4IL.test.model.jpa.enumeration.SomeEnum;
import com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk.Mailbox;
import com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk.Message;
import com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk2.Mailbox2;
import com.github.jjYBdx4IL.test.model.jpa.fkjoinoncomppk2.Message2;
import com.github.jjYBdx4IL.test.model.jpa.foreignkeyjoin.Post;
import com.github.jjYBdx4IL.test.model.jpa.foreignkeyjoin.UserFKJoin;
import com.github.jjYBdx4IL.test.model.jpa.foreignkeyjoin2.PostNoMappedBy;
import com.github.jjYBdx4IL.test.model.jpa.foreignkeyjoin2.UserNoMappedBy;
import com.github.jjYBdx4IL.test.model.jpa.misc.BlobStream;
import com.github.jjYBdx4IL.test.model.jpa.misc.Blog;
import com.github.jjYBdx4IL.test.model.jpa.misc.BlogWithFullTextIndex;
import com.github.jjYBdx4IL.test.model.jpa.misc.ExtendsBlog;
import com.github.jjYBdx4IL.test.model.jpa.misc.ExtendsBlogWithoutEntityAnnotation;
import com.github.jjYBdx4IL.test.model.jpa.misc.RowWithUniqueField;
import com.github.jjYBdx4IL.test.model.jpa.nullhandling.EntityWithPrimitives;
import com.github.jjYBdx4IL.test.model.jpa.refcomppk.Mailbox3;
import com.github.jjYBdx4IL.test.model.jpa.refcomppk.Message3;
import com.github.jjYBdx4IL.test.model.jpa.refcomppk.MessageMultiMBox;
import com.github.jjYBdx4IL.test.model.jpa.uniquecompidx.UniqueCompositeIndexEntity;
import com.github.jjYBdx4IL.utils.jdbc.ResultSetUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html_single/#mapping-declaration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional
public class PersistenceUnitTest {

    private static final Logger log = Logger.getLogger(PersistenceUnitTest.class.getName());

    @Inject
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private DataSource dataSource;

    @Test
    public void testNullHandling() {
        EntityWithPrimitives ewp = new EntityWithPrimitives();

        assertNull(ewp.getId());
        assertNull(ewp.getText());
        assertNull(ewp.getBooleanObject());
        assertNull(ewp.getLongObject());
        assertEquals(0L, ewp.getLongPrimitive());
        assertFalse(ewp.getBooleanPrimitive());

        em.persist(ewp);
        em.flush();

        assertNotNull(ewp.getId());
        assertNull(ewp.getText());
        assertNull(ewp.getBooleanObject());
        assertNull(ewp.getLongObject());
        assertEquals(0L, ewp.getLongPrimitive());
        assertFalse(ewp.getBooleanPrimitive());

        long newId = ewp.getId();
        ewp = em.find(EntityWithPrimitives.class, newId);

        assertNotNull(ewp.getId());
        assertNull(ewp.getText());
        assertNull(ewp.getBooleanObject());
        assertNull(ewp.getLongObject());
        assertEquals(0L, ewp.getLongPrimitive());
        assertFalse(ewp.getBooleanPrimitive());
    }

    @Test
    public void testCollectionDataSetWithValueList() {
        SomeValue v1 = new SomeValue();
        v1.setValue(1d);
        SomeValue v2 = new SomeValue();
        v2.setValue(2d);
        SomeValue v3 = new SomeValue();
        v3.setValue(3d);

        DataSetWithValueList dataSetWithValueList = new DataSetWithValueList();
        dataSetWithValueList.setDesc("desc1");
        dataSetWithValueList.getValues().add(v1);
        dataSetWithValueList.getValues().add(v2);

        em.persist(dataSetWithValueList);
        em.flush();

        Long id = dataSetWithValueList.getId();
        assertNotNull(id);
        assertNotNull(v1.getId());
        assertNotNull(v2.getId());

        dataSetWithValueList = em.find(DataSetWithValueList.class, id);
        assertNotNull(dataSetWithValueList);
        assertEquals(2, dataSetWithValueList.getValues().size());

        dataSetWithValueList.getValues().clear();
        dataSetWithValueList.getValues().add(v3);
        em.persist(dataSetWithValueList);
        em.flush();

        assertEquals(id, dataSetWithValueList.getId());
        assertNotNull(v3.getId());

        dataSetWithValueList = em.find(DataSetWithValueList.class, id);
        assertNotNull(dataSetWithValueList);
        assertEquals(1, dataSetWithValueList.getValues().size());
        assertEquals(3d, dataSetWithValueList.getValues().iterator().next().getValue(), 1e-8d);
    }

    @Test
    public void testCollectionDataSetWithValueListSetValues() {
        SomeValue v1 = new SomeValue();
        v1.setValue(1d);
        SomeValue v2 = new SomeValue();
        v2.setValue(2d);
        SomeValue v3 = new SomeValue();
        v3.setValue(3d);

        DataSetWithValueList dataSetWithValueList = new DataSetWithValueList();
        dataSetWithValueList.setDesc("desc1");
        List<SomeValue> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        dataSetWithValueList.setValues(list);

        em.persist(dataSetWithValueList);
        em.flush();

        Long id = dataSetWithValueList.getId();
        assertNotNull(id);
        assertNotNull(v1.getId());
        assertNotNull(v2.getId());

        dataSetWithValueList = em.find(DataSetWithValueList.class, id);
        assertNotNull(dataSetWithValueList);
        assertEquals(2, dataSetWithValueList.getValues().size());
    }

    @Test
    public void testUniqueCompositeIndexEntity() {
        User user1 = new User();
        user1.setName("User One");
        log.info(user1);
        em.persist(user1);
        log.info(user1);

        UniqueCompositeIndexEntity ent1 = new UniqueCompositeIndexEntity();
        ent1.setCreatedAt(new Date());
        ent1.setDescription("desc 1");
        ent1.setUser(user1);
        ent1.setName("ent1");
        log.info(ent1);
        em.persist(ent1);
        log.info(ent1);

        UniqueCompositeIndexEntity ent2 = new UniqueCompositeIndexEntity();
        ent2.setCreatedAt(new Date());
        ent2.setDescription("desc 1");
        ent2.setUser(user1);
        ent2.setName("ent2");
        log.info(ent2);
        em.persist(ent2);
        log.info(ent2);

        ent1 = new UniqueCompositeIndexEntity();
        ent1.setCreatedAt(new Date());
        ent1.setDescription("desc 1");
        ent1.setUser(user1);
        ent1.setName("ent1");
        log.info(ent1);
        try {
            em.persist(ent1);
            em.flush();
            fail();
        } catch (PersistenceException ex) {}
        log.info(ent1);
        
    }

    @Test
    public void testRefCompPK() {
        Mailbox3 mailbox = new Mailbox3();
        mailbox.setUid("USER1");
        mailbox.setId("MAILBOX1");
        mailbox.setDesc("my mailbox");
        em.persist(mailbox);

        log.info(mailbox);

        mailbox = new Mailbox3();
        mailbox.setUid("USER1");
        mailbox.setId("MAILBOX1");
        mailbox.setDesc("my mailbox 2");
        try {
            em.persist(mailbox);
            fail();
        } catch (EntityExistsException ex) {}

        Message3 message = new Message3();
        message.setTitle("my first post");
        message.setMailbox(mailbox);
        em.persist(message);

        message = new Message3();
        message.setTitle("my second post");
        message.setMailbox(mailbox);
        em.persist(message);
    }

    @Test
    public void testMultiRefCompPK() {
        Mailbox3 mailbox = new Mailbox3();
        mailbox.setUid("USER1");
        mailbox.setId("MAILBOX1");
        mailbox.setDesc("my first mailbox");
        em.persist(mailbox);
        log.info(mailbox);

        Mailbox3 mailbox2 = new Mailbox3();
        mailbox2.setUid("USER1");
        mailbox2.setId("MAILBOX2");
        mailbox2.setDesc("my second mailbox");
        em.persist(mailbox2);
        log.info(mailbox2);

        MessageMultiMBox message = new MessageMultiMBox();
        message.setTitle("my first post");
        List<Mailbox3> mailboxes = new ArrayList<>();
        mailboxes.add(mailbox);
        mailboxes.add(mailbox2);
        message.setMailbox(mailboxes);
        em.persist(message);

        Long msgId = message.getId();

        message = em.find(MessageMultiMBox.class, msgId);
        assertNotNull(message);
        assertEquals(2, message.getMailbox().size());

        Iterator<Mailbox3> it = message.getMailbox().iterator();
        it.next();
        it.remove();
        em.persist(message);

        message = em.find(MessageMultiMBox.class, msgId);
        assertNotNull(message);
        assertEquals(1, message.getMailbox().size());
    }

    @Test
    public void testEntityWithEnum() {
        EntityWithEnum entityWithEnum = new EntityWithEnum();
        entityWithEnum.someEnum = SomeEnum.ONE;
        em.persist(entityWithEnum);
        long oneId = entityWithEnum.id;

        entityWithEnum = new EntityWithEnum();
        entityWithEnum.someEnum = SomeEnum.TWO;
        em.persist(entityWithEnum);
        long twoId = entityWithEnum.id;

        entityWithEnum = em.find(EntityWithEnum.class, oneId);
        assertEquals(SomeEnum.ONE, entityWithEnum.someEnum);

        entityWithEnum = em.find(EntityWithEnum.class, twoId);
        assertEquals(SomeEnum.TWO, entityWithEnum.someEnum);
    }

    @Test
    public void testFKJoinOnCompPK() {
        Mailbox mailbox = new Mailbox();
        mailbox.setDesc("my mailbox");
        mailbox.setTitle("INBOX");
        em.persist(mailbox);
        em.flush();

        log.info(mailbox);

        Message message = new Message();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(1);
        mailbox.messages.add(message);
        em.persist(message);

        message = new Message();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(2);
        mailbox.messages.add(message);
        em.persist(message);
        em.flush();

        message = new Message();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(2);
        mailbox.messages.add(message);
        try {
            em.persist(message);
            fail();
        } catch(EntityExistsException ex) {
        }
    }

    @Test
    public void testFKJoinOnCompPK2() {
        Mailbox2 mailbox = new Mailbox2();
        mailbox.setDesc("my mailbox");
        mailbox.setTitle("INBOX");
        em.persist(mailbox);
        em.flush();

        log.info(mailbox);

        Message2 message = new Message2();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(1);
        mailbox.messages.add(message);
        em.persist(message);

        message = new Message2();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(2);
        mailbox.messages.add(message);
        em.persist(message);
        em.flush();

        message = new Message2();
        message.title = "my first post";
        message.content = "I make it short.";
        message.fromAddr = "me@localhost";
        message.toAddrs = "you@localhost, you2@localhost";
        message.mailbox = mailbox;
        message.setUid(2);
        mailbox.messages.add(message);
        try {
            em.persist(message);
            fail();
        } catch(EntityExistsException ex) {
        }
    }

    @Test
    public void testForeignKeyJoin() {
        UserFKJoin user = new UserFKJoin();
        Post post = new Post();
        post.setTitle("my first post");
        post.setContent("I make it short.");
        post.user = user;
        user.posts.add(post);
        em.persist(user);

        UserFKJoin user2 = em.find(UserFKJoin.class, user.getId());
        log.info(user);
        log.info(user2);

        Post post2 = new Post();
        post2.setTitle("my second post");
        post2.setContent("I make this one short, too!");
        post2.user = user;
        user.posts.add(post2);
        em.persist(user);

        log.info(user);
        log.info(user2);

        post.setTitle("corrected title");
        em.persist(post);
        em.flush();

        log.info(user);
        log.info(user2);
    }

    /**
     * omitting the mappedBy attribute of @OneToMany implies the
     * creation of a separate join table (crosstable).
     *
     * Does the use of a crosstable imply retaining the element order
     * in the @OneToMany relationship?
     */
    @Test
    public void testForeignKeyJoinWithoutMappedBy() {
        UserNoMappedBy user = new UserNoMappedBy();
        PostNoMappedBy post = new PostNoMappedBy();
        post.setTitle("my first post");
        post.setContent("I make it short.");
        post.user = user;
        user.posts.add(post);
        em.persist(user);

        UserNoMappedBy user2 = em.find(UserNoMappedBy.class, user.getId());
        log.info(user);
        log.info(user2);

        PostNoMappedBy post2 = new PostNoMappedBy();
        post2.setTitle("my second post");
        post2.setContent("I make this one short, too!");
        post2.user = user;
        user.posts.add(post2);
        em.persist(user);

        log.info(user);
        log.info(user2);

        post.setTitle("corrected title");
        em.persist(post);
        em.flush();

        log.info(user);
        log.info(user2);
    }

    @Test
    public void testLobStreaming() throws FileNotFoundException, IOException {
        log.info("testLobStreaming()");
        BlobStream bs = new BlobStream();
        byte[] data = "0123456789alsfhalsdjflkajsh flwehofh asdfsdf".getBytes("ASCII");
        long dataSize = data.length;
        InputStream is;
        is = new ByteArrayInputStream(data);
        // Hibernate-specific. Not working with MySQL5InnoDB dialect for larger objects (looks like
        // streaming is not implemented all the way through to the db server....).
        Session session = (Session)em.getDelegate();
        Blob blob = session.getLobHelper().createBlob(is, dataSize);
        bs.setBody(blob);
        assertEquals(dataSize, is.available());
        em.persist(bs);
        em.flush();
        assertEquals(0, is.available());
    }

    @Test
    public void testPersistence() {
        log.info("testPersistence()");
        Blog blog = em.find(Blog.class, -1L);
        Assert.assertNull(blog);

        blog = new Blog();
        em.persist(blog);

        log.info(blog.toString());
    }

    @Test
    public void testUniqueViolation() {
        log.info("testUniqueViolation()");
        RowWithUniqueField row = new RowWithUniqueField();
        row.setUniqueField("abc");
        em.persist(row);

        row = new RowWithUniqueField();
        row.setUniqueField("abc");
        try {
            em.persist(row);
            em.flush();
            fail();
        } catch (Exception ex) {
        }
    }

    @Test
    public void testCriteriaBuilderOrdering() {
        log.info("testCriteriaBuilderOrdering()");

        PrioritizedUser user = new PrioritizedUser();
        user.setPriority(1);
        user.setName("A");
        em.persist(user);

        user = new PrioritizedUser();
        user.setPriority(2);
        user.setName("B");
        em.persist(user);

        user = new PrioritizedUser();
        user.setPriority(3);
        user.setName("C");
        em.persist(user);

        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<PrioritizedUser> criteriaQuery = criteriaBuilder.createQuery(PrioritizedUser.class);
        final Root<PrioritizedUser> fromRoot = criteriaQuery.from(PrioritizedUser.class);
        Predicate predicate = criteriaBuilder.notEqual(
                fromRoot.get(PrioritizedUser_.name),
                "B");
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(criteriaBuilder.desc(fromRoot.get(PrioritizedUser_.priority)));

        List <PrioritizedUser> users = em.createQuery(criteriaQuery).getResultList();
        assertEquals(2, users.size());
        assertEquals(3, users.get(0).getPriority());
        assertEquals("C", users.get(0).getName());
        assertEquals(1, users.get(1).getPriority());
        assertEquals("A", users.get(1).getName());
    }

    @Test
    public void testPersistenceBinaryData() throws Exception {
        log.info("testPersistenceBinaryData()");
        byte[] rawdata = new byte[1000 * 1000 / 3]; // MySQL max packet size is 1 MB

        ExtendsBlog blog = new ExtendsBlog();
        blog.setRawData(rawdata);
        em.persist(blog);

        log.info("new id: " + blog.getId());

        blog = em.find(ExtendsBlog.class, blog.getId());
        Assert.assertNotNull(blog);

        Assert.assertEquals(blog.getRawData().length, rawdata.length);

        log.info(blog.toString());

        Connection conn = DataSourceUtils.getConnection(dataSource);
        String cat = null;
        String schema = "ROOT";

        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTableTypes();
        ResultSetUtils.dump(rs);
        rs = md.getTables(cat, schema, null, new String[]{"TABLE"});
        ResultSetUtils.dump(rs);
        rs = md.getColumns(cat, schema, null, null);
        ResultSetUtils.dump(rs);

        Statement st = conn.createStatement();
        rs = st.executeQuery("SELECT * FROM Blog");
        ResultSetUtils.dump(rs);
    }

    @Test
    public void testExtendsBlog() {
        log.info("testExtendsBlog()");
        ExtendsBlog blog = new ExtendsBlog();
        blog.setSender("sender");
        em.persist(blog);

        blog = em.find(ExtendsBlog.class, blog.getId());
        assertEquals(blog.getSender(), "sender");
    }

    @Test
    public void testExtendsBlogWithoutEntityAnnotation() {
        log.info("testExtendsBlogWithoutEntityAnnotation()");
        try {
            em.find(ExtendsBlogWithoutEntityAnnotation.class, 1L);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testDelete() {
        log.info("testDelete()");
        Blog blog = new Blog();
        em.persist(blog);

        long blogId = blog.getId();
        log.info("new id: " + blogId);

        blog = em.find(Blog.class, blogId);
        Assert.assertNotNull(blog);

        em.remove(blog);

        blog = em.find(Blog.class, blogId);
        Assert.assertNull(blog);
    }

    /**
     * <ul>
     * <li>http://docs.jboss.org/hibernate/search/3.4/reference/en-US/html_single/#search-query
     * <li>http://docs.jboss.org/hibernate/search/3.4/reference/en-US/html_single/#basic-mapping
     * <li>https://community.jboss.org/wiki/HibernateSearchAndOfflineTextExtraction
     * </ul>
     */
    @Test
    public void testHibernateSearch() throws SQLException {
        log.info("testHibernateSearch()");

        // for hibernate search to work, we need to have some objects committed to the database,
        // -> create a separate transaction and commit that one
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        // empty the table first
        @SuppressWarnings("unchecked")
        List<BlogWithFullTextIndex> list = entityManager.createQuery("select id from BlogWithFullTextIndex").getResultList();
        for (Object id : list.toArray()) {
            log.info("deleting blog id " + (long) id);
            BlogWithFullTextIndex blog = entityManager.find(BlogWithFullTextIndex.class, (long) id);
            log.info("Blog = " + blog);
            entityManager.remove(blog);
        }

        // populate the table
        for (long i = 1; i <= 10; i++) {
            BlogWithFullTextIndex blog = new BlogWithFullTextIndex();
            blog.setSubject("subject of blog no " + i);
            blog.setBody("body of blog no " + i);
            blog.setCreatedAt(new Date());
            entityManager.persist(blog);

            blog = entityManager.find(BlogWithFullTextIndex.class, blog.getId());
            blog.setSubject(blog.getSubject() + " appended");
            entityManager.persist(blog);
        }
        entityManager.getTransaction().commit();

        // perform the lucene search
        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(BlogWithFullTextIndex.class).get();

        org.apache.lucene.search.Query luceneQuery
                = b.keyword()
                .onField("subject").boostedTo(3)
                .matching("subject of blog no 3")
                .createQuery();
        javax.persistence.Query fullTextQuery
                = fullTextEntityManager.createFullTextQuery(luceneQuery, BlogWithFullTextIndex.class);

        @SuppressWarnings("unchecked")
        List<BlogWithFullTextIndex> result = fullTextQuery.getResultList();
        log.info(result.size());
        for (BlogWithFullTextIndex blog : result) {
            log.info(blog);
        }

        assertEquals("subject of blog no 3 appended", result.get(0).getSubject());
        assertEquals(10, result.size());
    }

    @Ignore // Derby and H2 produce a deadlock here
    @Test
    public void testTransactionIsolation() throws SQLException {
        log.info("testTransactionIsolation()");
        Statement st;
        ResultSet rs;

        Blog blog = new Blog();
        em.persist(blog);

        long blogId = blog.getId();
        log.info("new id: " + blogId);

        // not committed yet
        try (Connection conn = dataSource.getConnection()) {
            log.info("xact isolation = " + conn.getTransactionIsolation());
            log.info("Connection.TRANSACTION_READ_UNCOMMITTED = " + Connection.TRANSACTION_READ_UNCOMMITTED);
            log.info("Connection.TRANSACTION_READ_COMMITTED   = " + Connection.TRANSACTION_READ_COMMITTED);
            log.info("Connection.TRANSACTION_REPEATABLE_READ  = " + Connection.TRANSACTION_REPEATABLE_READ);
            log.info("Connection.TRANSACTION_SERIALIZABLE     = " + Connection.TRANSACTION_SERIALIZABLE);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Blog WHERE id = " + blogId);
            ResultSetUtils.dump(rs);
            assertNotNull(rs);
            assertFalse("no result sets for blog id " + blogId, rs.first());
        }

        try (Connection conn = dataSource.getConnection()) {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Blog WHERE id = " + blogId);
            assertTrue(rs.next());
        }
    }
}
