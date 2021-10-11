package kr.ac.daelim.bbr;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.member.MemberType;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationRepository;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final RegistrationRepository registrationRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        //USER
        Member member = Member.builder()
                .username("test")
                .password("test!")
                .name("테스터")
                .department("컴퓨터정보학부")
                .memberType(MemberType.USER)
                .build();
        memberRepository.save(member);

        //ADMIN
        Member admin = Member.builder()
                .username("admin")
                .password("test!")
                .name("관리자")
                .memberType(MemberType.ADMIN)
                .build();
        memberRepository.save(admin);

        for (int i = 1; i <= 101; i++) {
            //Book with AttachFile
            Book attachFile = Book.builder()
                    .title("title_f_"+i)
                    .author("author_f_"+i)
                    .publisher("publisher_f_"+i)
                    .datetime("2021-10-11")
                    .price(10000+i)
                    .attachFile(new UploadFile("1.png", "7d811386-890f-48ae-8c3e-be51948b2cb5.png"))
                    .stockQuantity(i)
                    .views(0)
                    .build();
            bookRepository.save(attachFile);
            Registration attichFileRegi = Registration.builder()
                    .book(attachFile)
                    .member(member)
                    .status(RegistrationStatus.COMP)
                    .department(member.getDepartment())
                    .build();
            registrationRepository.save(attichFileRegi);

            //Book with Thumbnail
            Book thumbnail = Book.builder()
                    .title("title_t_"+i)
                    .author("author_a_"+i)
                    .publisher("publisher_a_"+i)
                    .datetime("2012-01-11")
                    .price(20000+i)
                    .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3383739%3Ftimestamp%3D20190220072908")
                    .stockQuantity(i)
                    .views(0)
                    .build();
            bookRepository.save(thumbnail);
            Registration thumnailRegi = Registration.builder()
                    .book(thumbnail)
                    .member(member)
                    .status(RegistrationStatus.COMP)
                    .department(member.getDepartment())
                    .build();
            registrationRepository.save(thumnailRegi);
        }
    }
}