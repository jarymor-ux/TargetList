package ru.ostap.todolist.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.service.RegistrationService;
import ru.ostap.todolist.service.UserService;
import ru.ostap.todolist.utils.DtoConverter;
import ru.ostap.todolist.utils.UserErrorResponse;
import ru.ostap.todolist.utils.UserNotCreatedException;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

  private final UserService userService;
  private final DtoConverter dtoConverter;

  private final RegistrationService registrationService;

  @Autowired
  public RestUserController(
      UserService userService, DtoConverter dtoConverter, RegistrationService registrationService) {
    this.userService = userService;
    this.dtoConverter = dtoConverter;
    this.registrationService = registrationService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
    if (userService.getUserById(id).isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(dtoConverter.convertUserToUserDTO(userService.getUserById(id).get()));
  }

  @PutMapping("/create")
  public ResponseEntity<HttpStatus> create(
      @RequestBody @Valid UserDTO user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      StringBuilder errorMsg = new StringBuilder();

      List<FieldError> errors = bindingResult.getFieldErrors();
      for (FieldError error : errors) {
        errorMsg
            .append(error.getField())
            .append(" - ")
            .append(error.getDefaultMessage())
            .append(";");
      }
      throw new UserNotCreatedException(errorMsg.toString());
    }
    registrationService.saveWithDto(user);

    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {

    UserErrorResponse userErrorResponse =
        new UserErrorResponse(e.getMessage(), new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  private ResponseEntity<UserErrorResponse> handleException(UsernameNotFoundException e) {

    UserErrorResponse userErrorResponse =
        new UserErrorResponse(e.getMessage(), new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
  }
  // TODO:create more methods(last priority)
}
