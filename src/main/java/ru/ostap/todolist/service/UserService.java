package ru.ostap.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.ostap.todolist.models.User;
import ru.ostap.todolist.repository.UserRepository;
import ru.ostap.todolist.utils.DtoConverter;

@Service
public class UserService {

  private final UserRepository userRepository;
  @Autowired
  public UserService(UserRepository userRepository, DtoConverter dtoConverter, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.getUserById(id);
  }

  /*
   * Some users field return null
   * but in db these fields set automaticly
   */


  public Optional<User> getUserByUsername(String username){
    return userRepository.getUserByUsername(username);
  }


  public void save(User user) {
    userRepository.save(user);
  }
}
