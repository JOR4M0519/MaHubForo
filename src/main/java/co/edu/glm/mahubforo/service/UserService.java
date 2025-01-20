package co.edu.glm.mahubforo.service;

import co.edu.glm.mahubforo.repository.IUserRepository;
import co.edu.glm.mahubforo.domain.model.User;
import co.edu.glm.mahubforo.domain.dto.user.DataRegisterUser;
import co.edu.glm.mahubforo.domain.dto.user.DataAnswerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public DataAnswerUser registrarUsuario(DataRegisterUser datos) {
        String hashedPassword = passwordEncoder.encode(datos.contrasenia());
        DataRegisterUser datosConHashedPassword = new DataRegisterUser(datos.nombre(), datos.correoElectronico(), hashedPassword);
        User user = new User(datosConHashedPassword);
        user = usuarioRepository.save(user);
        return new DataAnswerUser(user.getId(), user.getNombre(), user.getCorreoElectronico());
    }



}
