package ingressunibank.bookstore.services;


import ingressunibank.bookstore.dto.CustomerDto;
import ingressunibank.bookstore.model.Customer;
import ingressunibank.bookstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    @InjectMocks
    private CustomerImpl customerService;
    List<Customer> customerList = new ArrayList<>();
    Customer ali5;
    Customer ali6;
    Customer user;
    @Mock
    private CustomerRepository customerRepository;


    @Before
    public void setUp() {

        ali5 = Customer.builder()
                .email("ali5@gmail.com")
                .name("ali5")
                .password("ali5")
                .roles("PUBLISHER")
                .personId(5)
                .build();

        ali6 = Customer.builder()
                .email("ali6@gmail.com")
                .name("ali6")
                .password("ali6")
                .roles("PUBLISHER")
                .personId(6)
                .build();
        user = Customer.builder()
                .email("ali7@gmail.com")
                .name("ali7")
                .password("ali7")
                .personId(7)
                .roles("USER")
                .build();
        customerList.add(ali5);
        customerList.add(ali6);
        customerList.add(user);

        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
    }

    @Test
    public void returnAllRegisteredCustomer() {
        List<Customer> list2 = customerService.customers();
        Assert.assertEquals(3, list2.size());
    }

//    @Test
//    public void addPersonTest() {
//        CustomerDto customerDto = CustomerDto.builder().name("Ali").password("1234").email("ali@gmail.com")
//                .roles("USER").build();
//        Mockito.when(customerRepository.save(customerDto)).thenReturn(customerDto);
//        Assert.assertEquals(customerDto, customerService.addCustomer(customerDto));
//    }

}

