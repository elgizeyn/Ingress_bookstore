package ingressunibank.bookstore.repositories;

import ingressunibank.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByName(String name);

    List<Customer> findAll();

//    CustomerDto save(CustomerDto dto);

}
