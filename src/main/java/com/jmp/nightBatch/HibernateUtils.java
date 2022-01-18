package com.jmp.nightBatch;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
//            Configuration configuration = new Configuration();
//            return configuration.buildSessionFactory(
//                    new StandardServiceRegistryBuilder()
//                            .applySettings(configuration.getProperties()).build());
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("this was an error building session factory for hibernate");
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
