package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository extends AbstractRepository<RoleEntity, Integer> {
}
