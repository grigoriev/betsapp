package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.GoldenGloveEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GoldenGlovesRepository extends AbstractRepository<GoldenGloveEntity, Integer> {
}
