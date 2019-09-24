package com.hello.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaInit {

    private static EntityManagerFactory emf;

    private JpaInit(){
    }

    public static JpaInit getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final JpaInit INSTANCE = new JpaInit();
    }

    public EntityManagerFactory getFactory(String persistenceUnitName) {
        return emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

}
