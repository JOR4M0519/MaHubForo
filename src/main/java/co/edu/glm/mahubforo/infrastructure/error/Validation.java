package co.edu.glm.mahubforo.infrastructure.error;


public class Validation extends RuntimeException{
    public Validation(String message) {
        super(message);
    }
}
