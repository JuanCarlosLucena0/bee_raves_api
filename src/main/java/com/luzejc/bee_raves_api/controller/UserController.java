package com.luzejc.bee_raves_api.controller;

import com.luzejc.bee_raves_api.dto.UserResponseDTO;
import com.luzejc.bee_raves_api.dto.UserUpdateDTO;
import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody User user){
        UserResponseDTO newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> patchUserById(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userDto){
        UserResponseDTO updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }

}
