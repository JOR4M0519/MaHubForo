package co.edu.glm.mahubforo.domain.dto.topic;

import co.edu.glm.mahubforo.domain.dto.user.DataUserIdName;

import java.time.LocalDateTime;
import java.util.List;

public record DataDetailedAnswerTopic(Long id,
                                      String titulo,
                                      String mensaje,
                                      LocalDateTime fechaDeCreacion,
                                      String curso,
                                      DataUserIdName autor,
                                      List<co.edu.glm.mahubforo.domain.dto.answer.DataDetailedAnswerTopic> respuestas) {
}
