package eu.grigoriev.suite;

import eu.grigoriev.controller.ControllersTest;
import eu.grigoriev.persistence.service.UsersRepositoryTest;
import eu.grigoriev.service.UserDetailsServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ControllersTest.class,
        UserDetailsServiceImplTest.class,
        UsersRepositoryTest.class
})
public class BetsTestSuite {
}
