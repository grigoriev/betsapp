package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CupsRepository extends AbstractRepository<CupEntity, Integer> {
}
