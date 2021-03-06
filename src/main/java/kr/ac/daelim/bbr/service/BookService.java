package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import kr.ac.daelim.bbr.utils.uploadfile.FileStore;
import kr.ac.daelim.bbr.web.book.dto.BookFileSaveRequestDto;
import kr.ac.daelim.bbr.web.book.dto.BookListResponseDto;
import kr.ac.daelim.bbr.web.book.dto.BookResponseDto;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final FileStore fileStore;

    @Transactional
    public Book save(BookFileSaveRequestDto bookSaveRequestDto) throws IOException {
        Optional<Book> findBook = bookRepository.findSameBook(bookSaveRequestDto.getTitle(), bookSaveRequestDto.getAuthor(), bookSaveRequestDto.getPublisher(), bookSaveRequestDto.getDatetime(), bookSaveRequestDto.getPrice());
        if (findBook.isEmpty()) {
            UploadFile uploadFile = fileStore.storeFile(bookSaveRequestDto.getAttachFile());
            return bookRepository.save(bookSaveRequestDto.toBook(uploadFile));
        } else {
            LocalDateTime now = LocalDateTime.now();
            findBook.get().updateRegistDt(now);
            return findBook.get();
        }
    }

    @Transactional
    public Book save(BookSaveRequestDto bookSaveRequestDto) {
        Optional<Book> findBook = bookRepository.findSameBook(bookSaveRequestDto.getTitle(), bookSaveRequestDto.getAuthor(), bookSaveRequestDto.getPublisher(), bookSaveRequestDto.getDatetime(), bookSaveRequestDto.getPrice());
        if (findBook.isEmpty()) {
            return bookRepository.save(bookSaveRequestDto.toBook());
        } else {
            LocalDateTime now = LocalDateTime.now();
            findBook.get().updateRegistDt(now);
            return findBook.get();
        }
    }

    @Transactional
    public BookResponseDto findByIdAddViews(Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("?????? ?????? ????????????. id=" + id));
        entity.addViews(); // views??????
        return new BookResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllModDesc() {
        return bookRepository.findAllModDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllViewsDesc() {
        return bookRepository.findAllViewsDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findByMyDept(Member member) {
        return bookRepository.findByMyDept(member).stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findByDept(String department) {
        return bookRepository.findByDept(department).stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findBySearchWord(String word) {
        return bookRepository.findBySearchWord(word).stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }
}
