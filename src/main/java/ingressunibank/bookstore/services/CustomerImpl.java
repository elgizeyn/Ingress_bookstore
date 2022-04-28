package ingressunibank.bookstore.services;

import ingressunibank.bookstore.dto.CustomerDto;
import ingressunibank.bookstore.model.Customer;
import ingressunibank.bookstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {


    private final CustomerRepository customerRepository;


    @Override
    public CustomerDto addCustomer(CustomerDto dto) {
        customerRepository.save(convertDto(dto));
        return dto;
    }

    @Override
    public List<Customer> customers() {
        return customerRepository.findAll();
    }


    private Customer convertDto(@Valid CustomerDto dto) {
        return Customer.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .roles(dto.getRoles().equals("USER") ? "USER" : "PUBLISHER")
                .build();
    }

}
