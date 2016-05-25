package eu.grigoriev.controller.data.loader;

import eu.grigoriev.model.response.general.SuccessResponse;
import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.service.SecurityRolesRepository;
import eu.grigoriev.persistence.service.UsersRepository;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(Mapping.INITIAL_USER.ROOT)
public class InitialUserController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SecurityRolesRepository securityRolesRepository;

    @RequestMapping(value = Mapping.INITIAL_USER.ADD_USERS, method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public SuccessResponse addUsers() {
        SecurityRoleEntity securityRoleUserEntity = securityRolesRepository.findByRole(Roles.USER);

        usersRepository.save(new UserEntity("ab", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "AB", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("ac", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "AC", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("ap", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "AP", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("ay", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "AY", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("en", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "EN", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("ik", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "IK", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("mg", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "MG", true, false, false, securityRoleUserEntity));

        usersRepository.save(new UserEntity("sg", "72aaf68408b9a37aa0f1e5faef33ef7d", "Sergey Grigoriev", "SG", true, false, false, securityRoleUserEntity));

        usersRepository.save(new UserEntity("sn", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "SN", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("sp", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "SP", true, false, false, securityRoleUserEntity));
        usersRepository.save(new UserEntity("vb", "72aaf68408b9a37aa0f1e5faef33ef7e", "", "VB", true, false, false, securityRoleUserEntity));

        return new SuccessResponse();
    }
}
