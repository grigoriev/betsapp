package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.TeamEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TeamsRepository extends AbstractRepository<TeamEntity, Integer> {
}
