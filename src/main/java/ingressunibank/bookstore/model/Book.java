package ingressunibank.bookstore.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "books")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Customer publisher;

    @Column(name = "author_name")
    private String authorName;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "price")
    private double price;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", publisher=" + publisher.getName() +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }

    public String getBookName(Book book) {
        return book.getBookName();
    }
}
