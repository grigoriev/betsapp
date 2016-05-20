package eu.grigoriev.controller.data.loader;

import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.service.SecurityRolesRepository;
import eu.grigoriev.persistence.service.UsersRepository;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.Roles;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(Mapping.INITIAL_DEPLOYMENT.ROOT)
public class InitialDeploymentController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SecurityRolesRepository securityRolesRepository;

    @RequestMapping(value = Mapping.INITIAL_DEPLOYMENT.ADD_USERS, method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public UserEntity[] clearAllAndAddUsers() {
        List<UserEntity> userEntities = usersRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            usersRepository.delete(userEntity.getId());
        }
        usersRepository.resetAutoincrement();

        List<SecurityRoleEntity> securityRolesEntities = securityRolesRepository.findAll();
        for (SecurityRoleEntity securityRoleEntity : securityRolesEntities) {
            securityRolesRepository.delete(securityRoleEntity.getId());
        }
        securityRolesRepository.resetAutoincrement();

        SecurityRoleEntity securityRoleAdminEntity = new SecurityRoleEntity();
        securityRoleAdminEntity.setRole(Roles.ADMIN);
        SecurityRoleEntity securityRoleUserEntity = new SecurityRoleEntity();
        securityRoleUserEntity.setRole(Roles.USER);

        securityRolesRepository.save(securityRoleAdminEntity);
        securityRolesRepository.save(securityRoleUserEntity);

        UserEntity userAdminEntity = new UserEntity();
        userAdminEntity.setUsername("grigoriev");
        userAdminEntity.setPassword("72aaf68408b9a37aa0f1e5faef33ef7d");
        userAdminEntity.setEnabled(true);
        userAdminEntity.setExpired(false);
        userAdminEntity.setLocked(false);
        userAdminEntity.setDisplay("Sergey Grigoriev");
        userAdminEntity.setSecurityRoleEntity(securityRoleAdminEntity);

        UserEntity userUserEntity = new UserEntity();
        userUserEntity.setUsername("sg");
        userUserEntity.setPassword("72aaf68408b9a37aa0f1e5faef33ef7d");
        userUserEntity.setEnabled(true);
        userUserEntity.setExpired(false);
        userUserEntity.setLocked(false);
        userUserEntity.setDisplay("Sergey Grigoriev");
        userUserEntity.setSecurityRoleEntity(securityRoleUserEntity);

        usersRepository.save(userAdminEntity);
        usersRepository.save(userUserEntity);

        return new UserEntity[]{userAdminEntity, userUserEntity};
    }
}
