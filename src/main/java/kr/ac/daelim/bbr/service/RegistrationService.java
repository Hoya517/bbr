package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationRepository;
import kr.ac.daelim.bbr.web.book.dto.BookFileSaveRequestDto;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import kr.ac.daelim.bbr.web.member.dto.RegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member loginMember, Book book, BookSaveRequestDto bookSaveRequestDto) {
        return registrationRepository.save(bookSaveRequestDto.toRegistration(book, loginMember)).getId();
    }

    @Transactional
    public Long save(Member loginMember, Book book, BookFileSaveRequestDto bookFileSaveRequestDto) {
        return registrationRepository.save(bookFileSaveRequestDto.toRegistration(book, loginMember)).getId();
    }

    @Transactional(readOnly = true)
    public List<RegistrationResponseDto> findAllDesc(Member member) {
        return registrationRepository.findAllDesc(member).stream()
                .map(RegistrationResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RegistrationResponseDto> findAllDesc() {
        return registrationRepository.findAllDesc().stream()
                .map(RegistrationResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long complete(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("?????? ???????????? ???????????? ????????????.")
        );
        registration.updateComp();

        Book book = bookRepository.findById(registration.getBook().getId()).orElseThrow(() -> new IllegalArgumentException("?????? ?????? ????????????. id=" + registration.getBook().getId()));
        book.addStock();  //?????? 1 ??????

        Member member = memberRepository.findById(registration.getMember().getId()).orElseThrow(() -> new IllegalArgumentException("?????? ??????????????? ????????????. id=" + registration.getMember().getId()));
        member.addPoint(book.getPrice());  //????????? ????????? ??????
        return registration.getId();
    }

    @Transactional
    public void cancel(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("?????? ???????????? ???????????? ????????????.")
        );
        registration.updateCancel();
    }
}
