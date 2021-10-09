package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegistrationResponseDto {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private RegistrationStatus status;

    public RegistrationResponseDto(Registration entity) {
        this.id = entity.getId();
        this.title = entity.getBook().getTitle();
        this.author = entity.getBook().getAuthor();
        this.price = entity.getBook().getPrice();
        this.status = entity.getStatus();
    }
}
