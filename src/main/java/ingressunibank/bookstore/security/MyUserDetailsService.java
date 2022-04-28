package ingressunibank.bookstore.security;

import ingressunibank.bookstore.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = personRepository.findByName(username);
        if (!person.isPresent())
            throw new UsernameNotFoundException("User not found: " + username);
        return new MyUserDetails(person.get());
    }
}
