package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.entity.TeamTypeEntity;
import eu.grigoriev.persistence.generic.AbstractRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SecurityRolesRepository extends AbstractRepository<SecurityRoleEntity, Integer> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SecurityRoleEntity findByRole(String role) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("SecurityRoleEntity.findByRole")
                .setString("role", role);

        return (SecurityRoleEntity) query.uniqueResult();
    }}
