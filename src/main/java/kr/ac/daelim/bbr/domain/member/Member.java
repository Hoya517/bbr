package kr.ac.daelim.bbr.domain.member;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.order.Order;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.exception.NotEnoughPointException;
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
    private int point;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    @Builder
    public Member(String name, String phone, String birth, String email, String code, String username, String password, String department, String emailAuthYn, String personalInfoTermYn, String serviceTermYn, MemberType memberType, int point) {
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
        this.memberType = memberType;
        this.point = point;
    }

    public void addPoint(Integer price) {
        this.point += price;
    }

    public void removePoint(int orderPrice) {
        int restPoint = this.point - orderPrice;
        if (restPoint < 0) {
            throw new NotEnoughPointException("need more point");
        }
        this.point -= orderPrice;
    }
}
