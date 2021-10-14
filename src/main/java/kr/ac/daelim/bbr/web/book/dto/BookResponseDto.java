package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Getter
public class BookResponseDto {

    private Long id;
    private String thumbnail;  // api url
    private UploadFile attachFile;  // upload file
    private String title;
    private String author;
    private String publisher;
    private String datetime;
    @NumberFormat(pattern = "###,###") private Integer price;
    @NumberFormat(pattern = "###,###") private int views;
    private List<Registration> registrations;

    public BookResponseDto(Book entity) {
        this.id = entity.getId();
        this.thumbnail = entity.getThumbnail();
        this.attachFile = entity.getAttachFile();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.datetime = entity.getDatetime();
        this.price = entity.getPrice();
        this.views = entity.getViews();
        this.registrations = entity.getRegistrations();
    }
}
