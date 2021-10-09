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
    public Long save(BookFileSaveRequestDto bookSaveRequestDto) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(bookSaveRequestDto.getAttachFile());
        return bookRepository.save(bookSaveRequestDto.toEntity(uploadFile)).getId();
    }

    @Transactional
    public Long save(BookSaveRequestDto bookSaveRequestDto) throws IOException {
        return bookRepository.save(bookSaveRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<BookListResponseDto> findAllDesc() {
        return bookRepository.findAllDesc().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    public BookResponseDto findById(Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new BookResponseDto(entity);
    }
}
