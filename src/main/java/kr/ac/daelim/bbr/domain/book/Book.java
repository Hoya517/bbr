package kr.ac.daelim.bbr.domain.book;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public class Book extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private String datetime;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, String datetime, int price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
    }
}
