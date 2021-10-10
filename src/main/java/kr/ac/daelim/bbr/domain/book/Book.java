package kr.ac.daelim.bbr.domain.book;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
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

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Registration> registrations = new ArrayList<>();

    private String title;
    private String author;
    private String publisher;
    private String datetime;
    private Integer price;
    private String isbn;
    private int stockQuantity;
    private int views;

    @Embedded
    private UploadFile attachFile;
    private String thumbnail;

    @Builder
    public Book(String title, String author, String publisher, String datetime, Integer price, String isbn, int stockQuantity, int views, String thumbnail, UploadFile attachFile) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.isbn = isbn;
        this.stockQuantity = stockQuantity;
        this.views = views;
        this.attachFile = attachFile;
        this.thumbnail = thumbnail;
    }

    public void addStock() {
        this.stockQuantity += 1;
    }

    public void addViews() {
        this.views += 1;
    }
}
