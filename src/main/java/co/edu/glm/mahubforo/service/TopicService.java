package co.edu.glm.mahubforo.service;

import co.edu.glm.mahubforo.domain.dto.topic.*;
import co.edu.glm.mahubforo.domain.model.Topic;
import co.edu.glm.mahubforo.domain.model.User;
import co.edu.glm.mahubforo.repository.ITopicRepository;
import co.edu.glm.mahubforo.repository.IUserRepository;
import co.edu.glm.mahubforo.infrastructure.validation.IValidationTopic;
import co.edu.glm.mahubforo.domain.dto.user.DataAnswerUser;
import co.edu.glm.mahubforo.domain.dto.user.DataUser;
import co.edu.glm.mahubforo.domain.dto.user.DataUserIdName;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final ITopicRepository topicoRepository;
    private final IUserRepository usuarioRepository;
    private final List<IValidationTopic> validadores;

    @Autowired
    public TopicService(ITopicRepository topicoRepository, IUserRepository usuarioRepository, List<IValidationTopic> validadores) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.validadores = validadores;
    }

    //post
    public DataAnswerTopic registrarTopico(DataTopicRegister datos) {
        validadores.forEach(v -> v.validar(datos));
        User user = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Topic topic = new Topic(datos,new DataUser(user));
        topic = topicoRepository.save(topic);
        return new DataAnswerTopic(topic.getId(), topic.getTitulo(), topic.getMensaje(),
                topic.getFechaDeCreacion(), topic.getCurso(),
                new DataAnswerUser(topic.getAutor().getId(),
                        topic.getAutor().getNombre(),
                        topic.getAutor().getCorreoElectronico()));
    }

    //get list
    public Page<DataListTopic> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllOrderedByFecha(pageable).map(DataListTopic::new);
    }

    public DataDetailedAnswerTopic obtenerTopicoYRespuestaPorId(Long id) {
        Topic topic = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con id " + id));

        List<co.edu.glm.mahubforo.domain.dto.answer.DataDetailedAnswerTopic> respuestas = topic.getRespuestas().stream()
                .map(respuesta -> new co.edu.glm.mahubforo.domain.dto.answer.DataDetailedAnswerTopic(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getFechaDeCreacion(),
                        new DataUserIdName(
                                respuesta.getAutor().getId(),
                                respuesta.getAutor().getNombre()
                        )
                )).toList();

        return new DataDetailedAnswerTopic(
                topic.getId(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaDeCreacion(),
                topic.getCurso(),
                new DataUserIdName(topic.getAutor().getId(), topic.getAutor().getNombre()),
                respuestas
        );
    }

    //put
    @Transactional
    public DataAnswerTopic actualizarTopico(Long id, DataUpdateTopic dataUpdateTopic) {
        Topic topic = topicoRepository.getReferenceById(id);
        topic.actualizarInformaciones(dataUpdateTopic);

        return new DataAnswerTopic(
                topic.getId(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaDeCreacion(),
                topic.getCurso(),
                new DataAnswerUser(topic.getAutor().getId(),
                        topic.getAutor().getNombre(),
                        topic.getAutor().getCorreoElectronico())
        );

    }

    @Transactional
    public void eliminarTopico(Long id){
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Topico no encontrada");
        }
        topicoRepository.deleteById(id);
    }

  }
