package co.edu.glm.mahubforo.domain.dto.answer;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisteryAnswer(@NotBlank(message = "El mensaje de la respuesta es obligatorio")
                                      @Size(max = 1000, message = "El mensaje no puede exceder los 1000 caracteres")
                                      String mensaje,
                                  @NotNull(message = "El ID del tópico es obligatorio")
                                      Long topicoId,
                                  @NotNull(message = "El ID del autor es obligatorio")
                                      Long autorId ) {}
