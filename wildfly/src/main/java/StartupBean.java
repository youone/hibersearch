import model.TestEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.elasticsearch.ElasticsearchQueries;
import org.hibernate.search.jpa.FullTextEntityManager;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless(name = "startupBean")
@TransactionManagement(value = TransactionManagementType.BEAN)
public class StartupBean {

    private static final Logger logger = Logger.getLogger(StartupBean.class);

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    @Resource
    private SessionContext sessionContext;

    public StartupBean() {}

    public void doStuff() {

        logger.info("DOING STUFF ..................................");
        logger.info(entityManager);
        logger.info(sessionContext);

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        Session session = entityManager.unwrap(org.hibernate.Session.class);
        FullTextSession fts = Search.getFullTextSession(session);
        MassIndexer mi = fts.createIndexer();
        try {
            logger.info("start1");
            mi.startAndWait();
//            logger.info("start2");
//            fullTextEntityManager.createIndexer().startAndWait();
            logger.info("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //elasticsearch
        List testEntities = fullTextEntityManager.createFullTextQuery(
                ElasticsearchQueries.fromJson("{ 'query': { 'match_all' : {} } }"),
                TestEntity.class
        ).getResultList();

        if (testEntities != null) {
            for (Object te : testEntities) {
                logger.info("................................ " + te.getClass().getName());
            }
        } else {
            logger.info("empty result");
        }

//        UserTransaction tx = sessionContext.getUserTransaction();
//
//        try {
//            tx.begin();
//
//            TestEntity testEntity = new TestEntity();
//            testEntity.setName("foo1");
//            testEntity.setVar(4);
//            entityManager.persist(testEntity);
//
//            TestEntity testEntity2 = new TestEntity();
//            testEntity2.setName("foo2");
//            testEntity2.setVar(6);
//            entityManager.persist(testEntity2);
//
//            tx.commit();
//        }
//        catch (Exception e) {
//            logger.error("cannot commit transaction", e);
//        }
//        finally {
////            entityManager.close();
//        }

    }

}
