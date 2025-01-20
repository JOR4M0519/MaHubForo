package co.edu.glm.mahubforo.domain.dto.answer;

import co.edu.glm.mahubforo.domain.dto.user.DataUserIdName;

import java.time.LocalDateTime;

public record DataDetailedAnswerTopic(Long id,
                                      String mensaje,
                                      LocalDateTime fechaDeCreacion,
                                      DataUserIdName autor) {
}
