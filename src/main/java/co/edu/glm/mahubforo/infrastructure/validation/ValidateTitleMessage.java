package co.edu.glm.mahubforo.infrastructure.validation;

import co.edu.glm.mahubforo.domain.dto.topic.DataTopicRegister;
import co.edu.glm.mahubforo.repository.ITopicRepository;
import co.edu.glm.mahubforo.infrastructure.error.Validation;
import org.springframework.stereotype.Component;

@Component
public class ValidateTitleMessage implements IValidationTopic {

    private final ITopicRepository topicoRepository;

    public ValidateTitleMessage(ITopicRepository topicoRepository){
        this.topicoRepository = topicoRepository;
    }


    @Override
    public void validar(DataTopicRegister datos) {
        if (topicoRepository.existsByTituloAndAutorId(datos.titulo(), datos.autorId())) {
            throw new Validation("El usuario ya tiene un tópico con este título.");
        }

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new Validation("Este título y mensaje ya existe.");
        }

        if (topicoRepository.existsByTitulo(datos.titulo())){
            throw new Validation("Este título ya existe");
        }

        if (topicoRepository.existsByMensaje(datos.mensaje())){
            throw new Validation("Este mensaje ya existe");
        }
    }
}
