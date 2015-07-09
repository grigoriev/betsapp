package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.GoldenBootEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GoldenBootsRepository extends AbstractRepository<GoldenBootEntity, Integer> {
}
