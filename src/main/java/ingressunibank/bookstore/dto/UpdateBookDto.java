package ingressunibank.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookDto {
    @NotBlank
    private String publisherName;
    @NotBlank
    private String publisherPassword;
    @NotBlank
    private String oldBookName;
    @NotBlank
    private String updatedBookName;
    @NotBlank
    private double updatedPrice;
}
