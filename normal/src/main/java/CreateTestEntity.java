import model.TestEntity;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateTestEntity
{
    private static final Logger logger = Logger.getLogger(CreateTestEntity.class);

    public static void main(String[] args)
    {
        logger.debug("starting application....");

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            TestEntity testEntity = new TestEntity();
            testEntity.setName("foo");
            testEntity.setVar(8);
            entityManager.persist(testEntity);

//            TestEntity testEntity2 = new TestEntity();
//            testEntity2.setName("bar");
//            testEntity2.setVar(8);
//            entityManager.persist(testEntity2);

            tx.commit();

        }
        catch (Exception e) {
            logger.error("cannot commit transaction", e);
            tx.rollback();
        }
        finally {
            entityManager.close();
        }

        emf.close();
    }
}