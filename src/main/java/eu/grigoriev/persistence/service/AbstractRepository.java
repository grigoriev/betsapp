package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.exception.EntityAlreadyExistsException;
import eu.grigoriev.persistence.exception.NoSuchEntityException;
import eu.grigoriev.persistence.generic.GenericEntity;
import eu.grigoriev.persistence.generic.GenericRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public abstract class AbstractRepository<Entity extends GenericEntity<PrimaryKey>, PrimaryKey extends Serializable> implements GenericRepository<Entity, PrimaryKey> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<Entity> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractRepository() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            entityClass = (Class<Entity>) actualTypeArguments[0];
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Entity create(Entity newInstance) {
        Session session = sessionFactory.getCurrentSession();

        boolean entityAlreadyExists = false;
        try {
            findById(newInstance.getPK());
            entityAlreadyExists = true;
        } catch (NoSuchEntityException e) {
            session.save(newInstance);
        }

        if (entityAlreadyExists) {
            throw new EntityAlreadyExistsException(newInstance.getPK().toString());
        }
        return newInstance;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Entity save(Entity entity) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(entity);

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Entity findById(PrimaryKey primaryKey) {
        if (primaryKey == null) {
            throw new NoSuchEntityException("PK is null");
        }

        Session session = sessionFactory.getCurrentSession();

        Entity entity = (Entity) session.get(entityClass, primaryKey);

        if (entity == null) {
            throw new NoSuchEntityException(primaryKey.toString());
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Entity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from " + entityClass.getSimpleName());
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Entity update(Entity transientObject) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(transientObject);
        session.update(transientObject);
        session.flush();
        return transientObject;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(PrimaryKey primaryKey) {
        Session session = sessionFactory.getCurrentSession();
        Entity entity = findById(primaryKey);
        session.delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from " + entityClass.getSimpleName());
        return (long) query.uniqueResult();
    }
}
