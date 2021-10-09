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

    private String title;
    private String author;
    private String publisher;
    private String datetime;
    private String thumbnail;
    private String clazz;
    private String state;
    private String etc;

    @Embedded
    private UploadFile attachFile;

    private Integer price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Registration> registrations = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, String datetime, Integer price, String thumbnail, String clazz, String state, String etc, UploadFile attachFile) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.thumbnail = thumbnail;
        this.attachFile = attachFile;
        this.clazz = clazz;
        this.state = state;
        this.etc = etc;
    }
}
