package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class BookFileSaveRequestDto {

    @NotBlank private String title;
    @NotBlank private String author;
    @NotBlank private String publisher;
    @NotBlank private String datetime;
    @NotBlank private Integer price;
    @NotNull private MultipartFile attachFile;
    private String isbn;
    private String clazz;
    private String state;
    private String etc;

    @Builder
    public BookFileSaveRequestDto(String title, String author, String publisher, String datetime, Integer price, MultipartFile attachFile, String isbn, String clazz, String state, String etc) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.attachFile = attachFile;
        this.isbn = isbn;
        this.clazz = clazz;
        this.state = state;
        this.etc = etc;
    }

    public Book toBook(UploadFile attachFile) {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .datetime(datetime)
                .price(price)
                .attachFile(attachFile)
                .isbn(isbn)
                .stockQuantity(0)
                .build();
    }

    public Registration toRegistration(Book book, Member member) {
        return Registration.builder()
                .book(book)
                .member(member)
                .status(RegistrationStatus.READY)
                .clazz(clazz)
                .state(state)
                .etc(etc)
                .build();
    }
}
