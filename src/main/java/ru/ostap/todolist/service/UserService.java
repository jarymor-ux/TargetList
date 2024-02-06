package ru.ostap.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.repository.UserRepository;
import ru.ostap.todolist.utils.DtoConverter;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final DtoConverter dtoConverter;

  @Autowired
  public UserService(UserRepository userRepository, DtoConverter dtoConverter) {
    this.userRepository = userRepository;
    this.dtoConverter = dtoConverter;
  }

  public User getUserById(Long id) {
    return userRepository.getUserById(id);
  }

  /*
   * Some users field return null
   * but in db these fields set automaticly
   */

  @SuppressWarnings("null")
  public void save(UserDTO userDTO) {
    User user = dtoConverter.convertUserDTOtoUser(userDTO);
    userRepository.save(user);
  }
}
