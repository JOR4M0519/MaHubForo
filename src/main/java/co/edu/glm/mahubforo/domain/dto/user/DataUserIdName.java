package co.edu.glm.mahubforo.domain.dto.user;

import co.edu.glm.mahubforo.domain.model.User;

public record DataUserIdName(Long id, String nombre) {
    public DataUserIdName(User user) {
        this(user.getId(), user.getNombre());
    }
}
