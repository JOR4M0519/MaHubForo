package co.edu.glm.mahubforo.controller;

import co.edu.glm.mahubforo.domain.dto.topic.*;
import co.edu.glm.mahubforo.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<DataAnswerTopic> registrarTopico(@Valid @RequestBody DataTopicRegister dataTopicRegister, UriComponentsBuilder uriComponentsBuilder) {
        DataAnswerTopic respuesta = topicService.registrarTopico(dataTopicRegister);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicService.listarTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailedAnswerTopic> obtenerTopico(@PathVariable Long id) {
        DataDetailedAnswerTopic respuestaTopico = topicService.obtenerTopicoYRespuestaPorId(id);

        return ResponseEntity.ok(respuestaTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataAnswerTopic> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
        DataAnswerTopic respuestaTopico = topicService.actualizarTopico(id, dataUpdateTopic);

        return ResponseEntity.ok(respuestaTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
            topicService.eliminarTopico(id);
            return ResponseEntity.noContent().build();

    }

}
