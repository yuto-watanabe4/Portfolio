package com.yuto.portfolio.config;

import com.yuto.portfolio.entity.User;
import com.yuto.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0){
            User user = new User();
            user.setUsername("admin");
            //SecurityConfigにあるencoderをここで安全に使う
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            userRepository.save(user);
            System.out.println("OK: TEST user created (admin / password)");
        }
    }
}
