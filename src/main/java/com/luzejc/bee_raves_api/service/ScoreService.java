package com.luzejc.bee_raves_api.service;

import com.luzejc.bee_raves_api.dto.ScoreRequestDTO;
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

    public ScoreRequestDTO createScore(Long userId, Long points){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        Score score = new Score();
        score.setUser(user);
        score.setPoints(points);
        scoreRepository.save(score);

        return toResponseDTO(score);
    }

    public List<ScoreRequestDTO> getAllScores(){
        return scoreRepository.findAllByOrderByPointsDesc()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ScoreRequestDTO> getScoresByUser(Long userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("Usuario no encontrado");
        }
        return scoreRepository.findByUserIdOrderByPointsDesc(userId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void deleteScore(Long id){
        if(!scoreRepository.existsById(id)){
            throw new RuntimeException("Puntuación no encontrado");
        }
        scoreRepository.deleteById(id);
    }

    private ScoreRequestDTO toResponseDTO(Score score){
        ScoreRequestDTO dto = new ScoreRequestDTO();
        dto.setId(score.getId());
        dto.setPoints(score.getPoints());
        dto.setUserId(score.getUser().getId());
        dto.setUsername(score.getUser().getUsername());
        dto.setCreatedAt(score.getCreatedAt());

        return dto;
    }

}
