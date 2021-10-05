package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.Getter;

@Getter
public class BookListResponseDto {

    private Long id;
    private String thumbnail;  // api url
    private UploadFile attachFile;  // upload file
    private String title;
    private String author;
    private String publisher;
    private String datetime;

    public BookListResponseDto(Book entity) {
        this.id = entity.getId();
        this.thumbnail = entity.getThumbnail();
        this.attachFile = entity.getAttachFile();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.datetime = entity.getDatetime();
    }
}
