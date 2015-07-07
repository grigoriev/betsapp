package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<UserEntity, Integer> {
}
