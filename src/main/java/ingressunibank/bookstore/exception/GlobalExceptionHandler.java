package ingressunibank.bookstore.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Log
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler({NoCustomerFoundException.class})
    protected ResponseEntity<String> handleNoCustomerFound(Exception e) {
        log.info(e + "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotValidCredentialsException.class)
    protected ResponseEntity<String> handleNotValidPasswordException(Exception e) {
        log.info(e + "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NoBookFoundException.class)
    protected ResponseEntity<String> handleNoBookException(Exception e) {
        log.info(e + "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<String> handle(Exception e) {
        log.info("internal-error: " + e.getMessage());
        return ResponseEntity.ok(e.getMessage());
    }

}
