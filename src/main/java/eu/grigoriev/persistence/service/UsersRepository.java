package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsersRepository extends AbstractRepository<UserEntity, Integer> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserEntity findByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("findByName")
                .setString("username", username);

        return (UserEntity) query.uniqueResult();
    }
}
