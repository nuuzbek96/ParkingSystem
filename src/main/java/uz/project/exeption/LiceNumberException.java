package uz.project.exeption;

public class LiceNumberException extends RuntimeException{
    public LiceNumberException() {
    }

    public LiceNumberException(String message) {
        super(message);
    }
}
