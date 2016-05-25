package eu.grigoriev.persistence.service;

import eu.grigoriev.persistence.entity.SecurityRoleEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.utils.security.Roles;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/spring-security.xml",
        "file:src/test/java/eu/grigoriev/persistence/service/junit-hibernate-context.xml"
})
public class UsersRepositoryTest {

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

    @Test
    public void testFindByName() throws Exception {
        UserEntity foundUserEntity = usersRepository.findByName("username");
        assertNotNull(foundUserEntity);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSave() throws Exception {
        usersRepository.save(testUserEntity);
    }

    @Test
    public void testFindNotFound() throws Exception {
        UserEntity foundUserEntity = usersRepository.findById(-11);
        assertNull(foundUserEntity);
    }

    @Test
    public void testFindById() throws Exception {
        UserEntity foundUserEntity = usersRepository.findById(testUserEntity.getId());
        assertEquals(testUserEntity.getUsername(), foundUserEntity.getUsername());
    }

    @Test
    public void testFindAll() throws Exception {
        List<UserEntity> userEntities = usersRepository.findAll();
        assertEquals(1, userEntities.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteByIdNotFound() throws Exception {
        usersRepository.delete(-11);
    }

    @Test
    public void testDelete() throws Exception {
        usersRepository.delete(testUserEntity.getId());
        List<UserEntity> userEntities = usersRepository.findAll();
        assertEquals(0, userEntities.size());
    }

    @Test
    public void testDeleteAll() throws Exception {
        usersRepository.deleteAll();
        List<UserEntity> userEntities = usersRepository.findAll();
        assertEquals(0, userEntities.size());
    }

    @Test
    public void testCount() throws Exception {
        long count = usersRepository.count();
        List<UserEntity> userEntities = usersRepository.findAll();
        assertEquals(count, userEntities.size());
    }
}