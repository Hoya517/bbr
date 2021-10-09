package kr.ac.daelim.bbr.domain.registration;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class Registration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @Builder
    public Registration(Book book, Member member, RegistrationStatus status) {
        this.book = book;
        this.member = member;
        this.status = status;
    }

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getRegistrations().add(this);
    }
    public void setBook(Book book) {
        this.book = book;
        book.getRegistrations().add(this);
    }
}
