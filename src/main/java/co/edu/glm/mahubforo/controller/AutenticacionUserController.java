package co.edu.glm.mahubforo.controller;

import co.edu.glm.mahubforo.service.UserService;
import co.edu.glm.mahubforo.domain.model.User;
import co.edu.glm.mahubforo.domain.dto.user.DataAuthenticatedUser;
import co.edu.glm.mahubforo.domain.dto.user.DataRegisterUser;
import co.edu.glm.mahubforo.domain.dto.user.DataAnswerUser;
import co.edu.glm.mahubforo.infrastructure.security.Token;
import co.edu.glm.mahubforo.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class AutenticacionUserController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AutenticacionUserController(AuthenticationManager authenticationManager,
                                       TokenService tokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/registro")
    public ResponseEntity<DataAnswerUser> registrarUsuario(@Valid @RequestBody DataRegisterUser datos,
                                                           UriComponentsBuilder uriBuilder) {
        DataAnswerUser respuesta = userService.registrarUsuario(datos);
        URI location = uriBuilder.path("/usuario/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(location).body(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> autenticarUsuario(@RequestBody @Valid DataAuthenticatedUser dataAuthenticatedUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataAuthenticatedUser.correoElectronico(),
                dataAuthenticatedUser.contrasenia()
        );
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new Token(jwtToken));
    }

}
