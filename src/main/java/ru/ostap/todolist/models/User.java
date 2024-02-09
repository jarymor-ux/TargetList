package ru.ostap.todolist.models;

import lombok.Data;
import lombok.Builder.Default;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Username should be not empty")
  @Size(min = 2, max = 15, message = "Username should be between 2 and 15")
  @NotNull(message = "Username should be not null")
  private String username;

  @Email(message = "Email must be valid")
  @NotBlank(message = "Email should be not empty")
  @NotNull(message = "Email should be not null")
  private String email;

  @NotBlank(message = "Password should be not empty")
  @NotNull(message = "Password should be not null")
  private String password;

  @Column(name = "role")
  private String role = "ROLE_USER"; //TODO:add not null and empty this field

  
  private boolean enabled = true; //TODO:add not null and empty this field

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> tasks;


}
