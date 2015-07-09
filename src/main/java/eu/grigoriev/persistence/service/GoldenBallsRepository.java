package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.GoldenBallEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GoldenBallsRepository extends AbstractRepository<GoldenBallEntity, Integer> {
}
