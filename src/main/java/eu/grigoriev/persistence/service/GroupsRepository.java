package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.GroupEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GroupsRepository extends AbstractRepository<GroupEntity, Integer> {
}
