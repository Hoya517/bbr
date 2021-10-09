package kr.ac.daelim.bbr.domain.member;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.order.Order;
import kr.ac.daelim.bbr.domain.registration.Registration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String birth;
    private String email;
    private String code;
    private String username;
    private String password;
    private String department;
    private String emailAuthYn;
    private String personalInfoTermYn;
    private String serviceTermYn;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Registration> registrations = new ArrayList<>();

    @Builder
    public Member(String name, String phone, String birth, String email, String code, String username, String password, String department, String emailAuthYn, String personalInfoTermYn, String serviceTermYn) {
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
}
