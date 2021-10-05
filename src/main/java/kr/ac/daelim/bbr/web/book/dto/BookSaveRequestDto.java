package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class BookSaveRequestDto {

    private String title;
    private String author;
    private String publisher;
    private String datetime;
    private Integer price;
    private String thumbnail;
    private MultipartFile attachFile;

    @Builder
    public BookSaveRequestDto(String title, String author, String publisher, String datetime, Integer price, String thumbnail, MultipartFile attachFile) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
        this.thumbnail = thumbnail;
        this.attachFile = attachFile;
    }

    public Book toEntity(UploadFile uploadFile) {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .datetime(datetime)
                .price(price)
                .thumbnail(thumbnail)
                .attachFile(uploadFile)
                .build();
    }

}
