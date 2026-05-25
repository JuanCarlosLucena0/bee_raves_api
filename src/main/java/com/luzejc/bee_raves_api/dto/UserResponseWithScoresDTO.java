package com.luzejc.bee_raves_api.dto;

import com.luzejc.bee_raves_api.entity.Score;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponseWithScoresDTO {    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private List<Score> scores;
}
