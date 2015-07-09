package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.BetEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BetsRepository extends AbstractRepository<BetEntity, Integer> {
}
