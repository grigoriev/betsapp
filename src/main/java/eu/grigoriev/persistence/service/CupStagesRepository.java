package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CupStagesRepository extends AbstractRepository<CupStageEntity, Integer> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public CupStageEntity findByCupAndName(CupEntity cup, String stage) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("CupStageEntity.findByCupAndName")
                .setEntity("cup", cup)
                .setString("stage", stage);

        return (CupStageEntity) query.uniqueResult();
    }
}
