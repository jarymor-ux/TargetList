package ru.ostap.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  @Autowired
  public UserService(UserRepository userRepository) {
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

  public List<User> findAll(){
      return userRepository.findAll();
  }

  public void delete(long id) {
    userRepository.deleteById(id);
  }


  public void update(long id, User updatedUser) {
    User userToBeUpdated = userRepository.findById(id).get();

    // добавляем по сути новую книгу (которая не находится в Persistence context), поэтому нужен save()
    updatedUser.setId(id);
    updatedUser.setTasks(userToBeUpdated.getTasks()); // чтобы не терялась связь при обновлении

    userRepository.save(updatedUser);
  }





}
