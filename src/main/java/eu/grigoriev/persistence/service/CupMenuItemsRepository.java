package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupMenuItemEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CupMenuItemsRepository extends AbstractRepository<CupMenuItemEntity, Integer> {
}
