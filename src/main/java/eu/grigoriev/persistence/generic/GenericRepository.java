package eu.grigoriev.persistence.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<Entity, PrimaryKey extends Serializable> {
    PrimaryKey save(Entity newInstance);

    Entity findById(PrimaryKey primaryKey);

    List<Entity> findAll();

    void delete(Entity entity);

    void delete(PrimaryKey primaryKey);

    long count();
}
