package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import kr.ac.daelim.bbr.utils.uploadfile.FileStore;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final FileStore fileStore;

    @Transactional
    public Long save(BookSaveRequestDto bookSaveRequestDto) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(bookSaveRequestDto.getAttachFile());
        return bookRepository.save(bookSaveRequestDto.toEntity(uploadFile)).getId();
    }
}
