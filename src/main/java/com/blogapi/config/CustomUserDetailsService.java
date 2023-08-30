package com.blogapi.config;

import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import com.blogapi.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(//this is entity user class// this will just go to db and check usernam eand email exist or not , if exist then it will store in users object. if not then throw exception.
                () -> new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));// this will throw exception by  UsernameNotFoundException buid in class, which is come from sprinsecurity.
        return new org.springframework.security.core.userdetails.User( //this is spring user class if class name are same then it will give fully qualify class// fully packege name isliye diye because you will get confuse.// return user class of springScurity is not a spring object. both are different.
                user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));//username which is getting from user object ,  email and password map to roe , which role of that person admin or users.
    }
    private Collection< ? extends GrantedAuthority> // which role of that person admin or users,for this perpose we create seperate method.
    mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}