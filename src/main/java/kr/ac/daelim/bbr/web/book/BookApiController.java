package kr.ac.daelim.bbr.web.book;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import kr.ac.daelim.bbr.web.book.dto.BookListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    /**
     * 검색 API - GET /api/books/searchWord
     * @param word
     */
    @GetMapping("/api/books/searchWord")
    public List<BookListResponseDto> searchWord(String word) {
        return bookService.findBySearchWord(word);
    }

    @GetMapping("/api/books/latest")
    public List<BookListResponseDto> searchLatestOrder() {
        return bookService.findAllModDesc();
    }

    @GetMapping("/api/books/popular")
    public List<BookListResponseDto> searchInOrderOfPopularity() {
        return bookService.findAllViewsDesc();
    }

    @GetMapping("/api/books/myDept")
    public List<BookListResponseDto> searchByMyDepartment(@Login Member loginMember) {
        return bookService.findByDept(loginMember.getDepartment());
    }

    @GetMapping("/api/books/dept")
    public List<BookListResponseDto> searchByMyDepartment(String department) {
        return bookService.findByDept(department);
    }
}
