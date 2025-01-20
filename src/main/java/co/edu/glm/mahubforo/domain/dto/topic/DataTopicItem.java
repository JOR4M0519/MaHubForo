package co.edu.glm.mahubforo.domain.dto.topic;

import co.edu.glm.mahubforo.domain.model.Topic;

public record DataTopicItem(Long id, String titulo) {
    public DataTopicItem(Topic topic) {
        this(topic.getId(), topic.getTitulo());
    }
}
