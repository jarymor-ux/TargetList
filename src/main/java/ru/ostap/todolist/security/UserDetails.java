package ru.ostap.todolist.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import ru.ostap.todolist.models.Task;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.service.TaskService;
import ru.ostap.todolist.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@RequiredArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final User user;
    private final TaskService taskService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public List<Task> getTasks() throws Exception {
        return taskService.getTasksByUser(this.user);
    }



    public User getUser() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
