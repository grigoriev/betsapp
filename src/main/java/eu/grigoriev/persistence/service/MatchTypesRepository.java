package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.entity.MatchTypeEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchTypesRepository extends AbstractRepository<MatchTypeEntity, Integer> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public MatchTypeEntity findByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("MatchTypeEntity.findByType")
                .setString("type", type);

        return (MatchTypeEntity) query.uniqueResult();
    }
}
