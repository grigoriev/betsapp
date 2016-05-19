package eu.grigoriev.persistence.generic;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<Entity, PrimaryKey extends Serializable> {
    PrimaryKey save(Entity newInstance);

    Entity update(Entity instance);

    boolean exists(PrimaryKey primaryKey);

    Entity findById(PrimaryKey primaryKey);

    List<Entity> findAll();

    void delete(PrimaryKey primaryKey);

    void deleteAll();

    void resetAutoincrement();

    long count();
}
