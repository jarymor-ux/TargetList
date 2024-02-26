package ru.ostap.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ostap.todolist.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> getUserById(Long id);

  Optional<User> getUserByUsername(String username);

  void deleteById(long id);
}
