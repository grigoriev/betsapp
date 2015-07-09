package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.PlayerEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayersRepository extends AbstractRepository<PlayerEntity, Integer> {
}
