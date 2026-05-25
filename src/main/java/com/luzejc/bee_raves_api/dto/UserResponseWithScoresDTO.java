package com.luzejc.bee_raves_api.dto;

import com.luzejc.bee_raves_api.entity.Score;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseWithScoresDTO {    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private List<Score> scores;
}
