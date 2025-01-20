package co.edu.glm.mahubforo.repository;

import co.edu.glm.mahubforo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByCorreoElectronico(String correoElectronico);
}
