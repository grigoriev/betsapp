package eu.grigoriev.service;

import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserEntity userEntity = userRepository.findByName(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return buildUserFromUserEntity(userEntity);
    }

    @Transactional(readOnly = true)
    User buildUserFromUserEntity(UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        boolean enabled = userEntity.isEnabled();
        boolean accountNonExpired = !userEntity.isExpired();
        boolean credentialsNonExpired = !userEntity.isExpired();
        boolean accountNonLocked = !userEntity.isLocked();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getSecurityRole().getRole()));

        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}