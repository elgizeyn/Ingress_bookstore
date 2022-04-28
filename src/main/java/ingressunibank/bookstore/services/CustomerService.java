package ingressunibank.bookstore.services;

import ingressunibank.bookstore.dto.CustomerDto;
import ingressunibank.bookstore.model.Customer;

import java.util.List;


public interface CustomerService {

    CustomerDto addCustomer(CustomerDto dto);

    List<Customer> customers();

}
