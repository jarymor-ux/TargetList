package ru.ostap.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ostap.todolist.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User getUserById(Long id);
}
