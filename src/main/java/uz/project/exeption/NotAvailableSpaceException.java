package uz.project.exeption;

public class NotAvailableSpaceException extends RuntimeException{
    public NotAvailableSpaceException() {
    }

    public NotAvailableSpaceException(String message) {
        super(message);
    }
}
