package com.luzejc.bee_raves_api.repository;

import com.luzejc.bee_raves_api.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserId(Long userId);
    List<Score> findByUserIdOrderByPointsDesc(Long userId);
}
