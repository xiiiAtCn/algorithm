package dsa.exception;

public class ExceptionQueueFull extends RuntimeException {
    public ExceptionQueueFull(String err) {
        super(err);
    }
}
