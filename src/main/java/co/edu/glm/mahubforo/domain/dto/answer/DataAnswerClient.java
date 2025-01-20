package co.edu.glm.mahubforo.domain.dto.answer;

import co.edu.glm.mahubforo.domain.dto.topic.DataTopicItem;
import co.edu.glm.mahubforo.domain.dto.user.DataUserIdName;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DataAnswerClient(@NotNull Long id,
                               String mensaje,
                               LocalDateTime fechaDeCreacion,
                               DataUserIdName usuario,
                               DataTopicItem topico,
                               boolean solucion
                                     ) {
}
