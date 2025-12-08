package tech.builrun.springsecurity.controllers;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import tech.builrun.springsecurity.controllers.dto.CreateUserDTO;
import tech.builrun.springsecurity.model.Role;
import tech.builrun.springsecurity.model.User;
import tech.builrun.springsecurity.repository.RoleRepository;
import tech.builrun.springsecurity.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO userDTO){

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if (userRepository.findByUsername(userDTO.username()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);


        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/users")   /// Rota especial para Admins
    public ResponseEntity<List<User>> listUser(){
        var users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }
}
