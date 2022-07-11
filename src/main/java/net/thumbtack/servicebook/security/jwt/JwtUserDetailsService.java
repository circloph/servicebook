package net.thumbtack.servicebook.security.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.thumbtack.servicebook.model.User;
import net.thumbtack.servicebook.repository.UserRepository;
import net.thumbtack.servicebook.security.JwtUserDetails;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new JwtUserDetails(user.getLogin(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getERole().getName())));
    }
    
}
