package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RegistrationResponseDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    @NumberFormat(pattern = "###,###")
    private Integer price;
    private RegistrationStatus status;
    private LocalDateTime createdDate;

    public RegistrationResponseDto(Registration entity) {
        this.id = entity.getId();
        this.title = entity.getBook().getTitle();
        this.author = entity.getBook().getAuthor();
        this.publisher = entity.getBook().getPublisher();
        this.price = entity.getBook().getPrice();
        this.status = entity.getStatus();
        this.createdDate = entity.getCreatedDate();
    }
}
