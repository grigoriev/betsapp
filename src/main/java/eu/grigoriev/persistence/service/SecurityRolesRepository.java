package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityRolesRepository extends AbstractRepository<SecurityRoleEntity, Integer> {
}
