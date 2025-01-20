package co.edu.glm.mahubforo.repository;

import co.edu.glm.mahubforo.domain.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t ORDER BY t.fechaDeCreacion ASC")
    Page<Topic> findAllOrderedByFecha(Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);

    boolean existsByTituloAndAutorId(String titulo, Long autorId);

    long countByAutorId(Long autorId);

}
