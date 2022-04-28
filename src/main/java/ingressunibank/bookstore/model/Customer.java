package ingressunibank.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "people")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    Integer personId;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    Set<Book> books;

    private String name;
    private String password;
    private String email;
    private String roles;


}