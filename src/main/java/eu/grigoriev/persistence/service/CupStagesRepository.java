package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CupStagesRepository extends AbstractRepository<CupStageEntity, Integer> {
}
