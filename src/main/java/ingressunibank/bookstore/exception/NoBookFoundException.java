package ingressunibank.bookstore.exception;


public class NoBookFoundException extends RuntimeException {


    public NoBookFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}



