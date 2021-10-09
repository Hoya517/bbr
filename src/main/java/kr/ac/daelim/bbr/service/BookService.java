package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final FileStore fileStore;

    @Transactional
    public Book save(BookFileSaveRequestDto bookSaveRequestDto) throws IOException {
        if (bookRepository.findByTitle(bookSaveRequestDto.getTitle()).isEmpty()) {
            UploadFile uploadFile = fileStore.storeFile(bookSaveRequestDto.getAttachFile());
            return bookRepository.save(bookSaveRequestDto.toBook(uploadFile));
        } else {
            return bookRepository.findByTitle(bookSaveRequestDto.getTitle()).get();
        }
    }

    @Transactional
    public Book save(BookSaveRequestDto bookSaveRequestDto) {
        if (bookRepository.findByTitle(bookSaveRequestDto.getTitle()).isEmpty()) {
            return bookRepository.save(bookSaveRequestDto.toBook());
        } else {
            return bookRepository.findByTitle(bookSaveRequestDto.getTitle()).get();
        }
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllDesc() {
        return bookRepository.findAllDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponseDto findById(Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        return new BookResponseDto(entity);
    }

    @Transactional
    public void addStock(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        book.addStock();
    }
}
