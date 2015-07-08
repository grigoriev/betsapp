package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<UserEntity, Integer> {
}
