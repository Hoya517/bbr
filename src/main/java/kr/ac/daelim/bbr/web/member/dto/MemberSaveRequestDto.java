package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberSaveRequestDto {

    @NotEmpty private String name;
    @NotEmpty private String phone;
    @NotEmpty private String birth;
    @NotEmpty private String email;
//    @NotEmpty
    private String code;
    @NotEmpty private String username;
    @NotEmpty private String password;
    @NotEmpty private String department;
//    @NotEmpty
    private String emailAuthYn;
    private Boolean personalInfoTermYn;
    private Boolean serviceTermYn;

    @Builder
    public MemberSaveRequestDto(String name, String phone, String birth, String email, String code, String username, String password, String department, String emailAuthYn, Boolean personalInfoTermYn, Boolean serviceTermYn) {
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.code = code;
        this.username = username;
        this.password = password;
        this.department = department;
        this.emailAuthYn = emailAuthYn;
        this.personalInfoTermYn = personalInfoTermYn;
        this.serviceTermYn = serviceTermYn;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .phone(phone)
                .birth(birth)
                .email(email)
                .code(code)
                .username(username)
                .password(password)
                .department(department)
                .emailAuthYn(emailAuthYn)
                .personalInfoTermYn(String.valueOf(personalInfoTermYn))
                .serviceTermYn(String.valueOf(serviceTermYn))
                .build();
    }
}
