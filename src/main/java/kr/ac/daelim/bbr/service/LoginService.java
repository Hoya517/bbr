package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null -> 로그인 실패
     */
    public Member login(String username, String password) {
        return memberRepository.findByUsername(username).stream()
                .filter(m -> m.getPassword().equals(password)).findFirst().orElse(null);
    }
}
