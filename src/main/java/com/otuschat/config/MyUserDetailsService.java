package com.otuschat.config;

import com.otuschat.dao.UserRepository;
import com.otuschat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByEmail(userName);

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userService.findById(id);

        return UserPrincipal.create(user);
    }

}
