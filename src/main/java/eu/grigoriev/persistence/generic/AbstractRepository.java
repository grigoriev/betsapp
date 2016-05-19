package eu.grigoriev.persistence.generic;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public abstract class AbstractRepository<Entity, PrimaryKey extends Serializable> implements GenericRepository<Entity, PrimaryKey> {

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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PrimaryKey save(Entity entity) {
        PrimaryKey primaryKey = (PrimaryKey) sessionFactory.getCurrentSession().save(entity);
        sessionFactory.getCurrentSession().flush();
        return primaryKey;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Entity update(Entity entity) {
        entity = (Entity) sessionFactory.getCurrentSession().merge(entity);
        sessionFactory.getCurrentSession().update(entity);
        sessionFactory.getCurrentSession().flush();
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean exists(PrimaryKey primaryKey) {
        Entity entity = findById(primaryKey);
        return (entity != null);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Entity findById(PrimaryKey primaryKey) {
        return (Entity) sessionFactory.getCurrentSession().get(entityClass, primaryKey);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Entity> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName()).list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(PrimaryKey primaryKey) {
        Entity entity = findById(primaryKey);
        delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll() {
        List<Entity> entities = findAll();
        for (Entity entity : entities) {
            delete(entity);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void resetAutoincrement() {
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("ALTER TABLE " + entityClass.getAnnotation(Table.class).name() + " AUTO_INCREMENT = 1");
        sqlQuery.executeUpdate();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return (long) sessionFactory.getCurrentSession().createQuery("select count(*) from " + entityClass.getSimpleName()).uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void delete(Entity entity) {
        sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
    }
}
