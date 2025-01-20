package co.edu.glm.mahubforo.domain.model;

import co.edu.glm.mahubforo.domain.dto.user.DataRegisterUser;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "userApp")
@Entity(name = "UserApp")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true, nullable = false)//Determina si una columna en la base de datos permite valores nulos (NULL) o no.
    private String correoElectronico;
    private String contrasenia;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topics;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public User() {
    }

    public User(Long id, String nombre, String correoElectronico, String contrasenia, List<Topic> topics, List<Answer> answers) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.topics = topics;
        this.answers = answers;
    }

    public User(DataRegisterUser datos) {
        this.nombre = datos.nombre();
        this.correoElectronico = datos.correoElectronico();
        this.contrasenia = datos.contrasenia();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setTopicos(List<Topic> topics) {
        this.topics = topics;
    }

    public void setRespuestas(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public List<Topic> getTopicos() {
        return topics;
    }

    public List<Answer> getRespuestas() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*
    cascade = CascadeType.ALL, orphanRemoval = true
     CascadeType.ALL Propaga las operaciones realizadas sobre la entidad padre hacia las entidades relacionadas como persistir, actualizar o eliminar.
     orphanRemoval = true elimina solo los registros que quedan "huérfanos" al ser removidos de la relación.
    ejemplo:Tienes un Usuario con 3 tópicos en su lista. Si eliminas uno de esos tópicos de la lista,
     JPA elimina ese Topico de la base de datos si orphanRemoval = true.
     Si no lo tienes configurado, el registro del tópico quedará huérfano en la base de datos.
     */
}
