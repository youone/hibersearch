import model.TestEntity;
import org.apache.log4j.Logger;
import org.hibernate.integrator.spi.Integrator;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ServiceLoader;

@Stateless(name = "writebean")
@TransactionManagement(value = TransactionManagementType.BEAN)
public class WriteBean {

    private static final Logger logger = Logger.getLogger(WriteBean.class);

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    @Resource
    private SessionContext sessionContext;

    public WriteBean() {}

    public void doStuff() {
        logger.info(entityManager);
        logger.info(sessionContext);

        UserTransaction tx = sessionContext.getUserTransaction();

        try {
            tx.begin();

            TestEntity testEntity = new TestEntity(1,2);
            testEntity.setName("foo1");
            testEntity.setVar(4);
//            testEntity.addLocation("POINT (4 5)");
            entityManager.persist(testEntity);

            TestEntity testEntity2 = new TestEntity(2,3);
            testEntity2.setName("foo2");
            testEntity2.setVar(6);
//            testEntity.addLocation("POINT (8 9)");
            entityManager.persist(testEntity2);

            tx.commit();
        }
        catch (Exception e) {
            logger.error("cannot commit transaction", e);
        }
        finally {
//            entityManager.close();
        }

    }

}
