package ru.ostap.todolist.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDTO {
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
  private String role;

  private boolean enabled;

  private List<TaskDTO> tasks;
}
