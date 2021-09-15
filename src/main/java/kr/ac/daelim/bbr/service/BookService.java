package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long save(BookSaveRequestDto dto) {
        return bookRepository.save(dto.toEntity()).getId();
    }
}
