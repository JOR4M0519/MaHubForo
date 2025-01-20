package co.edu.glm.mahubforo.infrastructure.validation;

import co.edu.glm.mahubforo.domain.dto.topic.DataTopicRegister;
import co.edu.glm.mahubforo.repository.ITopicRepository;
import co.edu.glm.mahubforo.infrastructure.error.*;
import org.springframework.stereotype.Component;

@Component
public class ValidateLimitTopicByUser implements IValidationTopic {

    private final ITopicRepository topicoRepository;
    private final int LIMITE_TOPICOS = 10;

    public ValidateLimitTopicByUser(ITopicRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }


    @Override
    public void validar(DataTopicRegister datos) {
        long cantidadTopicos = topicoRepository.countByAutorId(datos.autorId());
        if (cantidadTopicos >= LIMITE_TOPICOS) {
            throw new Validation("Un autor no puede tener más de " + LIMITE_TOPICOS + " tópicos.");
        }
    }
}
