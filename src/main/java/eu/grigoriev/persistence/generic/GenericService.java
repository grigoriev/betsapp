package eu.grigoriev.persistence.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<Entity, PrimaryKey extends Serializable> {
    Entity create(Entity newInstance);

    Entity save(Entity newInstance);

    Entity findById(PrimaryKey primaryKey);

    List<Entity> findAll();

    Entity update(Entity transientObject);

    void delete(PrimaryKey primaryKey);

    long count();
}
