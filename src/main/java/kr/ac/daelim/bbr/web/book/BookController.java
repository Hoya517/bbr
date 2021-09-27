package kr.ac.daelim.bbr.web.book;

import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/search")
    public String searchBook() {
        return "book/book_search";
    }

    @GetMapping("/book/choice")
    public String getBookInfo() {

        return "/book/add";
    }

    @GetMapping("/book/add")
    public String addBook(
            @ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto,
            @Param("title") String title
    ) {
        return "book/book_add_info";
    }

    @PostMapping("/book/add")
    public String addBook(@ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto) {
        bookService.save(bookSaveRequestDto);
        return "redirect:/";
    }
}
