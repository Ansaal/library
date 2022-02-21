package de.jhulsch.library.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class AbstractRepository {

    abstract protected EntityManager getEntityManager();
}
