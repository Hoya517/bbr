package kr.ac.daelim.bbr.web.book;

import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.web.book.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/books")
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public String searchBook() {
        return "book_search2";
    }

    @GetMapping("/add")
    public String addBookForm(@ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto) {
        return "book/book_add_info";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto) throws IOException {
        bookService.save(bookSaveRequestDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book/book_info";
    }
}
