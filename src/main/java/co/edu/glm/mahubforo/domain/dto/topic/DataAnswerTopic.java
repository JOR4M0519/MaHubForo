package co.edu.glm.mahubforo.domain.dto.topic;

import co.edu.glm.mahubforo.domain.dto.user.DataAnswerUser;

import java.time.LocalDateTime;


public record DataAnswerTopic(Long id,
                              String titulo,
                              String mensaje,
                              LocalDateTime fechaDeCreacion,
                              String nombreCurso,
                              DataAnswerUser autor) {
}
