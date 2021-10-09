package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationRepository;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
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

    @Transactional
    public Long save(Member loginMember, Book book) {
        Registration registration = Registration.builder()
                                                 .member(loginMember)
                                                 .book(book)
                                                 .status(RegistrationStatus.READY)
                                                 .build();
        return registrationRepository.save(registration).getId();
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
    public void complete(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        registration.updateComp();
    }

    @Transactional
    public void cancel(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        registration.updateCancel();
    }
}
