package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.dto.LoginRequestDTO;
import com.luzejc.bee_raves_api.dto.LoginResponseDTO;
import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.exception.ResourceNotFoundException;
import com.luzejc.bee_raves_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponseDTO(token);
    }
}
