package Exceptions;

public class wrongUsernameOrPasswordException extends RuntimeException {
    public wrongUsernameOrPasswordException(String message) {
        super(message);
    }
}
