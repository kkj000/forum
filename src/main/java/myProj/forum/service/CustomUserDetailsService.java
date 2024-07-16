package myProj.forum.service;

import myProj.forum.domain.User;
import myProj.forum.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByLoginId(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found: " + username));

        /*
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLoginId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

         */

        return new CustomUserDetails(
                user.getLoginId(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())),
                user.getId()
        );
    }




}
