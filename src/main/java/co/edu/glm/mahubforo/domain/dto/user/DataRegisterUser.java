package co.edu.glm.mahubforo.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterUser(@NotBlank(message = "El nombre es obligatorio")
                                   String nombre,
                               @NotBlank(message = "El correo es obligatorio")
                                   @Email(message = "El correo no tiene un formato válido")
                                   String correoElectronico,
                               @NotBlank(message = "La contraseña es obligatoria")
                                   String contrasenia) {
}
