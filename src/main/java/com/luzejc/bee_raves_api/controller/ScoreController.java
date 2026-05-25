package com.luzejc.bee_raves_api.controller;

import com.luzejc.bee_raves_api.dto.ScoreRequestDTO;
import com.luzejc.bee_raves_api.service.ScoreService;
import com.luzejc.bee_raves_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public ResponseEntity<ScoreRequestDTO> createScore(@RequestBody ScoreRequestDTO requestDTO){
        ScoreRequestDTO newScore = scoreService.createScore(requestDTO.getUserId() , requestDTO.getPoints());
        return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
    }

    @GetMapping
    public ResponseEntity<List<ScoreRequestDTO>> getAllScores(){
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScoreRequestDTO>> getAllScoresByUser(@PathVariable Long userId){
        return ResponseEntity.ok(scoreService.getScoresByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id){
        scoreService.deleteScore(id);
        return ResponseEntity.noContent().build();
    }

}
