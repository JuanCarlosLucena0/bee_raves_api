package com.luzejc.bee_raves_api.controller;

import com.luzejc.bee_raves_api.dto.ScoreRequestDTO;
import com.luzejc.bee_raves_api.entity.Score;
import com.luzejc.bee_raves_api.service.ScoreService;
import com.luzejc.bee_raves_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody ScoreRequestDTO requestDTO){
        Score newScore = scoreService.createScore(requestDTO.getUserid() , requestDTO.getPoints());
        return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
    }

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores(){
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Score>> getAllScoresByUser(@PathVariable Long userId){
        return ResponseEntity.ok(scoreService.getScoresByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id){
        scoreService.deleteScore(id);
        return ResponseEntity.noContent().build();
    }

}
