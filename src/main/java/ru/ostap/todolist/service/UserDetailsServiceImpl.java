package ru.ostap.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import ru.ostap.todolist.models.User;
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService{
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<User> user = userService.getUserByUsername(s);
       
       if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
       }

       return new ru.ostap.todolist.security.UserDetails(user.get());
    }


}
