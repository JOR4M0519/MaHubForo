package co.edu.glm.mahubforo.infrastructure.security;

import co.edu.glm.mahubforo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionUserService implements UserDetailsService {
    private final IUserRepository usuarioRepository;

    @Autowired
    public AutenticacionUserService(IUserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
