package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.MatchTypeEntity;
import eu.grigoriev.persistence.entity.TeamEntity;
import eu.grigoriev.persistence.entity.TeamTypeEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TeamTypesRepository extends AbstractRepository<TeamTypeEntity, Integer> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TeamTypeEntity findByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("TeamTypeEntity.findByType")
                .setString("type", type);

        return (TeamTypeEntity) query.uniqueResult();
    }
}
