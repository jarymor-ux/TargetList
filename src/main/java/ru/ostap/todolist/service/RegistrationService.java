package ru.ostap.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ostap.todolist.dto.UserDTO;
import ru.ostap.todolist.models.User;
import ru.ostap.todolist.utils.DtoConverter;
@Service
public class RegistrationService {

    private  final DtoConverter dtoConverter;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    @Autowired
    public RegistrationService(DtoConverter dtoConverter, PasswordEncoder passwordEncoder, UserService userService) {
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public void save(UserDTO userDTO) {
        User user = dtoConverter.convertUserDTOtoUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }

}
