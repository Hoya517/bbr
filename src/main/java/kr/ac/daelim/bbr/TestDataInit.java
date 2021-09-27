package kr.ac.daelim.bbr;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

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
    }

}