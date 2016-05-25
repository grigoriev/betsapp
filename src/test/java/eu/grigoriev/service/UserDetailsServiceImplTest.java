package eu.grigoriev.service;

import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.service.SecurityRolesRepository;
import eu.grigoriev.persistence.service.UsersRepository;
import eu.grigoriev.utils.security.Roles;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/spring-security.xml",
        "file:src/test/java/eu/grigoriev/persistence/service/junit-hibernate-context.xml"
})
public class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    SecurityRolesRepository securityRolesRepository;

    @Autowired
    UsersRepository usersRepository;

    UserEntity testUserEntity;

    @Before
    public void setUp() throws Exception {
        Integer securityRoleId = securityRolesRepository.save(new SecurityRoleEntity(Roles.USER));

        testUserEntity = new UserEntity(
                "username",
                DigestUtils.md5Hex("password"),
                "User Name",
                "UN",
                true,
                false,
                false,
                securityRolesRepository.findById(securityRoleId)
        );

        usersRepository.save(testUserEntity);
    }

    @After
    public void tearDown() throws Exception {
        usersRepository.deleteAll();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNotFound() throws Exception {
        userDetailsService.loadUserByUsername("invalid-username");
    }

    @Test
    public void testLogin() throws Exception {
        UserDetails username = userDetailsService.loadUserByUsername("username");

        assertEquals(testUserEntity.getPassword(), username.getPassword());
        Set<String> authorities = AuthorityUtils.authorityListToSet(username.getAuthorities());
        assertTrue(authorities.contains(Roles.USER));
    }
}