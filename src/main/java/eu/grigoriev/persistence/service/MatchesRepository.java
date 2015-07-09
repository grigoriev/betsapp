package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MatchesRepository extends AbstractRepository<MatchEntity, Integer> {
}
