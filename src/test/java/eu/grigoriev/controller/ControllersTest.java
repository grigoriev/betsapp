package eu.grigoriev.controller;

import eu.grigoriev.utils.security.Roles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/spring-security.xml"
})
public class ControllersTest {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    private void login() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Roles.USER));
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken("username", "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void invalidRole() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_INVALID"));
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken("username", "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void logout() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testIndexIndex() throws Exception {
        login();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index/index"));
        logout();
    }

    @Test
    public void testInvalidRole() throws Exception {
        invalidRole();
        try {
            mockMvc.perform(get("/admin"));
        } catch (Throwable e) {
            assertTrue(e.getCause() instanceof AccessDeniedException);
        }
        logout();
    }

    @Test
    public void testAuthLogin() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"));
    }
}
