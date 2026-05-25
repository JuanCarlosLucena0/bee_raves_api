package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.dto.UserResponseDTO;
import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email ya en uso");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Nombre de usuario en uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser){
        User user = userRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Usuario no encontrado") );

        if (hasValue(updatedUser.getUsername())){
            user.setUsername(updatedUser.getUsername());
        }
        if(hasValue(updatedUser.getEmail())){
            user.setEmail(updatedUser.getEmail());
        }
        if(hasValue(updatedUser.getPassword())){
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    private UserResponseDTO toResponseDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }


    private boolean hasValue(String value) {
        return value != null && !value.isBlank();
    }

}
