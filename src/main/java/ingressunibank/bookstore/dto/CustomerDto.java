package ingressunibank.bookstore.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull
    @Size(min = 2, message = "Name's size should be more than 2")
    private String name;
    @NotBlank
    @Size(min = 4, max = 6)
    private String password;
    @Email
    private String email;
    @NotNull
    private String roles;

}
