package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberSaveRequestDto {

    @NotBlank private String name;
    @NotBlank private String phone;
    @NotBlank private String birth;
    @NotBlank private String email;
    @NotBlank private String code;
    @NotBlank private String emailAuthYn;
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String department;
    private Boolean personalInfoTermYn;
    private Boolean serviceTermYn;

    @Builder
    public MemberSaveRequestDto(String name, String phone, String birth, String email, String code, String emailAuthYn, String username, String password, String department, Boolean personalInfoTermYn, Boolean serviceTermYn) {
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.email = email;
        this.code = code;
        this.emailAuthYn = emailAuthYn;
        this.username = username;
        this.password = password;
        this.department = department;
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
                .emailAuthYn(emailAuthYn)
                .username(username)
                .password(password)
                .department(department)
                .personalInfoTermYn(String.valueOf(personalInfoTermYn))
                .serviceTermYn(String.valueOf(serviceTermYn))
                .memberType(MemberType.USER)
                .point(0)
                .build();
    }
}
