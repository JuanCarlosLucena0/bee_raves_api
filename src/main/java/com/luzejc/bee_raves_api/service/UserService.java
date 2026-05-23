package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("El email ya está en uso");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Nombre de usuario en uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }
}
