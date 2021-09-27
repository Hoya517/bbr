package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto) {
        return memberRepository.save(memberSaveRequestDto.toEntity()).getId();
    }
}
