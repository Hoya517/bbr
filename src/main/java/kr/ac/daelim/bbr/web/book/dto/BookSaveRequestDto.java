package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class BookSaveRequestDto {

    @NotBlank private String title;
    @NotBlank private String author;
    @NotBlank private String publisher;
    @NotBlank private String datetime;
    @NotBlank private Integer price;
    @NotBlank private String thumbnail;
    private String department;
    private String isbn;
    private String clazz;
    private String state;
    private String etc;

    @Builder
    public BookSaveRequestDto(String title, String author, String publisher, String datetime, Integer price, String thumbnail, String department, String isbn, String clazz, String state, String etc) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.thumbnail = thumbnail;
        this.department = department;
        this.isbn = isbn;
        this.clazz = clazz;
        this.state = state;
        this.etc = etc;
    }

    public Book toBook() {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .datetime(datetime)
                .price(price)
                .thumbnail(thumbnail)
                .isbn(isbn)
                .stockQuantity(0)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
    }

    public Registration toRegistration(Book book, Member member) {
        return Registration.builder()
                .book(book)
                .member(member)
                .status(RegistrationStatus.READY)
                .department(department)
                .clazz(clazz)
                .state(state)
                .etc(etc)
                .build();
    }
}
