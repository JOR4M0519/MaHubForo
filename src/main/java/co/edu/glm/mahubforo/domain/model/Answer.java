package co.edu.glm.mahubforo.domain.model;


import co.edu.glm.mahubforo.domain.dto.answer.DataUpdateAnswer;
import co.edu.glm.mahubforo.domain.dto.answer.DataRegisteryAnswer;
import co.edu.glm.mahubforo.domain.dto.topic.DataTopic;
import co.edu.glm.mahubforo.domain.dto.user.DataUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "answer")
@Entity(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//nullable indica si el mensaje puede ser nula o no en este caso esta en false lo que significa que el msj no puede ser nulo e
    private String mensaje;

    private LocalDateTime fechaDeCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)//nullable indica si la clave foranea puede ser nula o no obviamente no queremos que las llaves foraneas sean nulas
    private User autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topic topic;

    private boolean solucion;

    public Answer(DataRegisteryAnswer datos, DataTopic dtoTopico, DataUser dtoUsuario){
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.autor = dtoUsuario.user();
        this.topic = dtoTopico.topic();
        this.solucion = true;
    }

    public void actualizarInformacion(DataUpdateAnswer datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public void setTopico(Topic topic) {
        this.topic = topic;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public User getAutor() {
        return autor;
    }

    public Topic getTopico() {
        return topic;
    }

    public boolean isSolucion() {
        return solucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
