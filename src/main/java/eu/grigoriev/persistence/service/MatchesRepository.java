package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.entity.TeamTypeEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MatchesRepository extends AbstractRepository<MatchEntity, Integer> {
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<MatchEntity> findByCupAndStage(CupEntity cup, CupStageEntity cupStage) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("MatchEntity.findByCupAndStage")
                .setEntity("cup", cup)
                .setEntity("cupStage", cupStage);

        return (List<MatchEntity>) query.list();
    }
}
