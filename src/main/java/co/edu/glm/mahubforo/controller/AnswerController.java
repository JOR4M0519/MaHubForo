package co.edu.glm.mahubforo.controller;

import co.edu.glm.mahubforo.domain.dto.answer.DataUpdateAnswer;
import co.edu.glm.mahubforo.domain.dto.answer.DataAnswerClient;
import co.edu.glm.mahubforo.domain.dto.answer.DataRegisteryAnswer;
import co.edu.glm.mahubforo.service.AnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<DataAnswerClient> registrarRespuesta(@Valid @RequestBody DataRegisteryAnswer dataRegisteryAnswer, UriComponentsBuilder uriComponentsBuilder){
        DataAnswerClient respuesta = answerService.crearRespuesta(dataRegisteryAnswer);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAnswerClient> obtenerRespuestaPorId(@PathVariable Long id) {
        DataAnswerClient respuesta = answerService.obtenerRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataAnswerClient> actualizarRespuesta(@PathVariable Long id,
                                                                @Valid @RequestBody DataUpdateAnswer dataUpdateAnswer) {
        DataAnswerClient respuesta = answerService.actualizarRespuesta(id, dataUpdateAnswer);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        answerService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }


}
