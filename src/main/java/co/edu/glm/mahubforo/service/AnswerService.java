package co.edu.glm.mahubforo.service;

import co.edu.glm.mahubforo.domain.dto.answer.DataUpdateAnswer;
import co.edu.glm.mahubforo.domain.dto.answer.DataAnswerClient;
import co.edu.glm.mahubforo.domain.dto.answer.DataRegisteryAnswer;
import co.edu.glm.mahubforo.domain.model.Answer;
import co.edu.glm.mahubforo.domain.dto.topic.DataTopic;
import co.edu.glm.mahubforo.domain.dto.topic.DataTopicItem;
import co.edu.glm.mahubforo.domain.model.Topic;
import co.edu.glm.mahubforo.repository.IAnswerRepository;
import co.edu.glm.mahubforo.repository.ITopicRepository;
import co.edu.glm.mahubforo.repository.IUserRepository;
import co.edu.glm.mahubforo.domain.model.User;
import co.edu.glm.mahubforo.domain.dto.user.DataUser;
import co.edu.glm.mahubforo.domain.dto.user.DataUserIdName;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final IAnswerRepository respuestaRepository;
    private final IUserRepository usuarioRepository;
    private final ITopicRepository topicoRepository;

    public AnswerService(IAnswerRepository respuestaRepository, IUserRepository usuarioRepository, ITopicRepository topicoRepository) {
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
    }


    //post
    public DataAnswerClient crearRespuesta(DataRegisteryAnswer dataRegisteryAnswer) {
        User autor = usuarioRepository.findById(dataRegisteryAnswer.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        Topic topic = topicoRepository.findById(dataRegisteryAnswer.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        Answer answer = new Answer(dataRegisteryAnswer, new DataTopic(topic), new DataUser(autor));
        respuestaRepository.save(answer);

        return new DataAnswerClient(answer.getId(), answer.getMensaje(), answer.getFechaDeCreacion(), new DataUserIdName(autor), new DataTopicItem(topic), answer.isSolucion());
    }

    //get
    public DataAnswerClient obtenerRespuestaPorId(Long id) {
        Answer answer = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));
        return new DataAnswerClient(answer.getId(), answer.getMensaje(), answer.getFechaDeCreacion(),
                new DataUserIdName(answer.getAutor()),
                new DataTopicItem(answer.getTopico()),
                answer.isSolucion());
    }

    //put
    @Transactional
    public DataAnswerClient actualizarRespuesta(Long id, DataUpdateAnswer dataUpdateAnswer) {
        Answer answer = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Respuesta no encontrada"));
        answer.actualizarInformacion(dataUpdateAnswer);
        return new DataAnswerClient(answer.getId(), answer.getMensaje(), answer.getFechaDeCreacion(),
                new DataUserIdName(answer.getAutor()),
                new DataTopicItem(answer.getTopico()),
                answer.isSolucion());
    }

    //delete
    @Transactional
    public void eliminarRespuesta(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new EntityNotFoundException("Respuesta no encontrada");
        }
        respuestaRepository.deleteById(id);
    }



}
