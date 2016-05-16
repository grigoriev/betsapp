package eu.grigoriev.controller.test;

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
@RequestMapping(Mapping.TEST.ROOT)
public class TestController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SecurityRolesRepository securityRolesRepository;

    @RequestMapping(value = Mapping.TEST.ADD_TEST_USER, method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public UserEntity[] clearAllAndAddTestUser() {
        List<UserEntity> userEntities = usersRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            usersRepository.delete(userEntity.getId());
        }

        List<SecurityRoleEntity> securityRolesEntities = securityRolesRepository.findAll();
        for (SecurityRoleEntity securityRoleEntity : securityRolesEntities) {
            securityRolesRepository.delete(securityRoleEntity.getId());
        }

        SecurityRoleEntity securityRoleAdminEntity = new SecurityRoleEntity();
        securityRoleAdminEntity.setRole(Roles.ADMIN);
        SecurityRoleEntity securityRoleUserEntity = new SecurityRoleEntity();
        securityRoleUserEntity.setRole(Roles.USER);

        securityRolesRepository.save(securityRoleAdminEntity);
        securityRolesRepository.save(securityRoleUserEntity);

        UserEntity userAdminEntity = new UserEntity();
        userAdminEntity.setUsername("admin");
        userAdminEntity.setPassword(DigestUtils.md5Hex("admin"));
        userAdminEntity.setEnabled(true);
        userAdminEntity.setExpired(false);
        userAdminEntity.setLocked(false);
        userAdminEntity.setDisplay("Admin Name");
        userAdminEntity.setSecurityRoleEntity(securityRoleAdminEntity);

        UserEntity userUserEntity = new UserEntity();
        userUserEntity.setUsername("username");
        userUserEntity.setPassword(DigestUtils.md5Hex("password"));
        userUserEntity.setEnabled(true);
        userUserEntity.setExpired(false);
        userUserEntity.setLocked(false);
        userUserEntity.setDisplay("User Name");
        userUserEntity.setSecurityRoleEntity(securityRoleUserEntity);

        usersRepository.save(userAdminEntity);
        usersRepository.save(userUserEntity);

        return new UserEntity[]{userAdminEntity, userUserEntity};
    }
}
