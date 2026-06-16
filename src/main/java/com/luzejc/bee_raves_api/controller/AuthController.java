package com.luzejc.bee_raves_api.controller;

import com.luzejc.bee_raves_api.dto.LoginRequestDTO;
import com.luzejc.bee_raves_api.dto.LoginResponseDTO;
import com.luzejc.bee_raves_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response= authService.login(request);
        return ResponseEntity.ok(response);
    }
}
