package kr.ac.daelim.bbr;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .username("test")
                .password("test!")
                .name("테스터")
                .build();
        memberRepository.save(member);

        Book book1 = Book.builder()
                .attachFile(new UploadFile("",""))
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3383739%3Ftimestamp%3D20190220072908")
                .title("Java1")
                .author("Savitch")
                .publisher("Pearson")
                .datetime("2016-02-01")
                .price(10000)
                .build();
        bookRepository.save(book1);

        Book book2 = Book.builder()
                .attachFile(new UploadFile("1.png", "04b22c3b-795d-4940-b7a9-3867141f07f0.png"))
                .thumbnail("")
                .title("Java2")
                .author("Savitch")
                .publisher("Pearson")
                .datetime("2016-02-01")
                .price(20000)
                .build();
        bookRepository.save(book2);
    }

}