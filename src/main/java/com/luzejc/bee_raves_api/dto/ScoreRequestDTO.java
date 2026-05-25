package com.luzejc.bee_raves_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoreRequestDTO {
    private Long id;
    private Long userId;
    private Long points;
    private String username;
    private LocalDateTime createdAt;
}
