package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.dto.UserResponseDTO;
import com.luzejc.bee_raves_api.dto.UserUpdateDTO;
import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.exception.DuplicateResourceException;
import com.luzejc.bee_raves_api.exception.ResourceNotFoundException;
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
    private UserResponseDTO userResponseDTO;

    public UserResponseDTO createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateResourceException("Email ya en uso");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new DuplicateResourceException("Nombre de usuario en uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);

    }
//
    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<UserResponseDTO> getUserById(Long id){
        return userRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO updatedUser){
        User user = userRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Usuario no encontrado") );

        if (hasValue(updatedUser.getUsername())){
            user.setUsername(updatedUser.getUsername());
        }
        if(hasValue(updatedUser.getEmail())){
            user.setEmail(updatedUser.getEmail());
        }
        if(hasValue(updatedUser.getPassword())){
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(user);
        return toResponseDTO(user);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado");
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
