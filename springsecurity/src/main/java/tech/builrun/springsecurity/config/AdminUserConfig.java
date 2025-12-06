package tech.builrun.springsecurity.config;


import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.builrun.springsecurity.model.Role;
import tech.builrun.springsecurity.model.User;
import tech.builrun.springsecurity.repository.RoleRepository;
import tech.builrun.springsecurity.repository.UserRepository;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    @Transactional
    public void run(String... args) throws Exception{

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("Admin");

        if (userAdmin.isPresent()){
            System.out.println("Admin já existe");
        } else {
            var user = new User();
            user.setUsername("Admin");
            user.setPassword(bCryptPasswordEncoder.encode("123"));
            user.setRoles(Set.of((roleAdmin)));
            userRepository.save(user);
        }
    }
}
