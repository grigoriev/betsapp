package eu.grigoriev.persistence.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<Entity, PrimaryKey extends Serializable> {
    PrimaryKey save(Entity newInstance);

    boolean exists(PrimaryKey primaryKey);

    Entity findById(PrimaryKey primaryKey);

    List<Entity> findAll();

    void delete(PrimaryKey primaryKey);

    void deleteAll();

    long count();
}
