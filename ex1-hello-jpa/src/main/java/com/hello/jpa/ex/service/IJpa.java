package com.hello.jpa.ex.service;

import javax.persistence.EntityManager;

public interface IJpa {
    public void basic(EntityManager em);
    public void persistenceContext(EntityManager em);
    public void flush(EntityManager em);
    public void remove(EntityManager em);
    public void detached(EntityManager em);
    public void entityMapping(EntityManager em);
    public void primaryKey(EntityManager em);

    public void tableModeling(EntityManager em);
}
