package eu.grigoriev.persistence.generic;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        return (PrimaryKey) sessionFactory.getCurrentSession().save(entity);
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
    public void delete(Entity entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(PrimaryKey primaryKey) {
        Entity entity = findById(primaryKey);
        delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return (long) sessionFactory.getCurrentSession().createQuery("select count(*) from " + entityClass.getSimpleName()).uniqueResult();
    }
}
