package ingressunibank.bookstore.security;

import ingressunibank.bookstore.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class MyUserDetails implements UserDetails {
    private final String userName;
    private final String password;
    private final String email;
    private final List<GrantedAuthority> authorities;


    public MyUserDetails(Customer person) {
        this.userName = person.getName();
        this.email = person.getEmail();
        this.password = person.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(person.getRoles()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
