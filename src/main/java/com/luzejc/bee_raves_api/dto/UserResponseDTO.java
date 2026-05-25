package com.luzejc.bee_raves_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
