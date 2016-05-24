package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.TeamTypeEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CupsRepository extends AbstractRepository<CupEntity, Integer> {
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public CupEntity findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("CupEntity.findByName")
                .setString("name", name);

        return (CupEntity) query.uniqueResult();
    }
}
