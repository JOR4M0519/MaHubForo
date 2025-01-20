package co.edu.glm.mahubforo.domain.dto.topic;

import co.edu.glm.mahubforo.domain.model.Topic;

import java.time.LocalDateTime;

public record DataListTopic(Long id,
                            String titulo,
                            String mensaje,
                            LocalDateTime fechaDeCreacion,
                            String curso,
                            String autor){
public DataListTopic(Topic topic){
    this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getFechaDeCreacion(), topic.getCurso(), topic.getAutor().getNombre());
}
}
