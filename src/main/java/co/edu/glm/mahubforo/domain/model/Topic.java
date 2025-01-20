package co.edu.glm.mahubforo.domain.model;

import co.edu.glm.mahubforo.domain.dto.topic.DataUpdateTopic;
import co.edu.glm.mahubforo.domain.dto.topic.DataTopicRegister;
import co.edu.glm.mahubforo.domain.dto.user.DataUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Table(name = "topic", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "mensaje"})})
@Entity(name = "Topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(nullable = false)
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private String curso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Topic() {
    }

    public Topic(Long id, String titulo, String mensaje, LocalDateTime fechaDeCreacion, String curso, User autor, List<Answer> answers) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.curso = curso;
        this.autor = autor;
        this.answers = answers;
    }

    public Topic(DataTopicRegister dto, DataUser dtoUsuario){
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.curso = dto.curso();
        this.autor = dtoUsuario.user();
    }

    public void actualizarInformaciones(DataUpdateTopic datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null) {
            this.curso=datos.curso();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public void setRespuestas(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public String getCurso() {
        return curso;
    }

    public User getAutor() {
        return autor;
    }

    public List<Answer> getRespuestas() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
