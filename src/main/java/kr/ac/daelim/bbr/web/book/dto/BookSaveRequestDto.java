package kr.ac.daelim.bbr.web.book.dto;

import kr.ac.daelim.bbr.domain.book.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookSaveRequestDto {

    private String title;
    private String author;
    private String publisher;
    private String datetime;
    private int price;

    @Builder
    public BookSaveRequestDto(String title, String author, String publisher, String datetime, int price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.datetime = datetime;
        this.price = price;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .datetime(datetime)
                .price(price)
                .build();
    }
}
