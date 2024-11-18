package Exceptions;

public class noPermissionException extends RuntimeException {
    public noPermissionException(String message) {
        super(message);
    }
}
