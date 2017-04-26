package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import persistence.*;

/**
 * Created by blackbird on 4/22/17.
 */
public class Database {

    private final SessionFactory sessionFactory =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(TwoReplicaLoadBalancingTests.class)
                    .addAnnotatedClass(ThreeReplicaLoadBalancingTest.class)
                    .addAnnotatedClass(OneReplicaProcessFailureTest.class)
                    .addAnnotatedClass(TwoReplicaProcessFailureTest.class)
                    .addAnnotatedClass(OneReplicaHttpFailureTest.class)
                    .addAnnotatedClass(TwoReplicaHttpFailureTest.class)
                    .addAnnotatedClass(OneReplicaNodeFailureTest.class)
                    .addAnnotatedClass(TwoReplicaNodeFailureTest.class)
                    .addAnnotatedClass(OneReplicaAutoScalingTest.class)
                    .buildSessionFactory();

    public void save(Object table){
        Session session = sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.save( table );
            session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

}
