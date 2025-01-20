package co.edu.glm.mahubforo.repository;

import co.edu.glm.mahubforo.domain.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer, Long> {
}
