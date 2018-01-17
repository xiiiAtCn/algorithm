package dsa.exception;

public class ExceptionQueueEmpty extends RuntimeException {
    public ExceptionQueueEmpty(String err) {
        super(err);
    }
}
