package ingressunibank.bookstore.exception;

public class NotValidCredentialsException extends RuntimeException {


    public NotValidCredentialsException(String message) {
        super(message);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

