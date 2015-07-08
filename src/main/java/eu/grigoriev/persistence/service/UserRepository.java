package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<UserEntity, Integer> {

    public UserEntity findByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("findByName")
                .setString("username", username);

        return (UserEntity) query.uniqueResult();
    }
}
