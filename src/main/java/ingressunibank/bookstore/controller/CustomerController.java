package ingressunibank.bookstore.controller;

import ingressunibank.bookstore.dto.CustomerDto;
import ingressunibank.bookstore.exception.NotValidCredentialsException;
import ingressunibank.bookstore.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping({"/home", "/"})
    public String index() {
        return "Welcome to Book Store";
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> addPerson(@Valid @RequestBody CustomerDto dto, BindingResult result) {
        if (result.hasErrors())
            throw new NotValidCredentialsException("Credentials are not valid");

        customerService.addCustomer(dto);
        return ResponseEntity.ok(dto.getName() + " is registered as the " + dto.getRoles());
    }

}
