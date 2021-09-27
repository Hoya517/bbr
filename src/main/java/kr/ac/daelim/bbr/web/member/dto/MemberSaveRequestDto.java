package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberSaveRequestDto {

    @NotEmpty private String username;
    @NotEmpty private String password;
    @NotEmpty private String name;

    @Builder
    public MemberSaveRequestDto(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .build();
    }
}
