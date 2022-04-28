package ingressunibank.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotNull
    private String publisherName;
    @NotNull
    private String authorName;
    @NotNull
    private String bookName;
    @NotNull
    private double price;
}
