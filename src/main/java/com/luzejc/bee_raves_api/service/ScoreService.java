package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.entity.Score;
import com.luzejc.bee_raves_api.entity.User;
import com.luzejc.bee_raves_api.repository.ScoreRepository;
import com.luzejc.bee_raves_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public Score createScore(Long userId, Long points){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        Score score = new Score();
        score.setUser(user);
        score.setPoints(points);

        return scoreRepository.save(score);
    }

    public List<Score> getAllScores(){
        return scoreRepository.findAllByOrderByPointsDesc();
    }

    public List<Score> getScoresByUser(Long userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("Usuario no encontrado");
        }
        return scoreRepository.findByUserIdOrderByPointsDesc(userId);
    }

    public void deleteScore(Long id){
        if(!scoreRepository.existsById(id)){
            throw new RuntimeException("Puntuación no encontrado");
        }
        scoreRepository.deleteById(id);
    }

}
