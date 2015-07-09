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
    public UserEntity clearAllAndAddTestUser() {
        List<UserEntity> userEntities = usersRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            usersRepository.delete(userEntity.getId());
        }

        List<SecurityRoleEntity> securityRolesEntities = securityRolesRepository.findAll();
        for (SecurityRoleEntity securityRoleEntity : securityRolesEntities) {
            securityRolesRepository.delete(securityRoleEntity.getId());
        }

        SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
        securityRoleEntity.setRole(Roles.USER);

        securityRolesRepository.save(securityRoleEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username");
        userEntity.setPassword(DigestUtils.md5Hex("password"));
        userEntity.setEnabled(true);
        userEntity.setExpired(false);
        userEntity.setLocked(false);
        userEntity.setDisplay("User Name");
        userEntity.setSecurityRoleEntity(securityRoleEntity);

        usersRepository.save(userEntity);

        return userEntity;
    }
}
