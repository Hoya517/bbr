package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
public class BookSaveRequestDto {

    @NotBlank private String title;
    @NotBlank private String author;
    @NotBlank private String publisher;
    @NotBlank private String datetime;
    @NotBlank private Integer price;
    @NotBlank private String thumbnail;
    private String clazz;
    private String state;
    private String etc;

    @Builder
    public BookSaveRequestDto(String title, String author, String publisher, String datetime, Integer price, String thumbnail, String clazz, String state, String etc) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.thumbnail = thumbnail;
        this.clazz = clazz;
        this.state = state;
        this.etc = etc;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .datetime(datetime)
                .price(price)
                .thumbnail(thumbnail)
                .clazz(clazz)
                .state(state)
                .etc(etc)
                .build();
    }
}
