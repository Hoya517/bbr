package kr.ac.daelim.bbr.web.book;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.service.RegistrationService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import kr.ac.daelim.bbr.web.book.dto.BookFileSaveRequestDto;
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
    private final RegistrationService registrationService;

    @GetMapping("/search")
    public String searchBook(@Login Member loginMember, Model model) {
        log.info("[ GET /books/search ]");
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "book/book_search";
    }

    @GetMapping("/add")
    public String addBookForm(@Login Member loginMember, Model model, @RequestParam(required = true) String t,
                              @ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto) {
        log.info("[ GET /books/add ]");
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "book/book_add";
    }

    @GetMapping("/add_file")
    public String addBookFormWithFile(@Login Member loginMember, Model model,
                                      @ModelAttribute("bookFileSaveRequestDto") BookFileSaveRequestDto bookFileSaveRequestDto) {
        log.info("[ GET /books/add_file ]");
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "book/book_add_file";
    }

    @PostMapping("/add")
    public String addBook(@Login Member loginMember, @ModelAttribute("bookSaveRequestDto") BookSaveRequestDto bookSaveRequestDto) {
        Book book = bookService.save(bookSaveRequestDto);
        registrationService.save(loginMember, book, bookSaveRequestDto);
        return "redirect:/members/myPage/registrations";
    }

    @PostMapping("/add_file")
    public String addBookFileWithFile(@Login Member loginMember, @ModelAttribute("bookFileSaveRequestDto") BookFileSaveRequestDto bookFileSaveRequestDto) throws IOException {
        Book book = bookService.save(bookFileSaveRequestDto);
        registrationService.save(loginMember, book, bookFileSaveRequestDto);
        return "redirect:/members/myPage/registrations";
    }

    @GetMapping("/{id}")
    public String findByIdAddViews(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findByIdAddViews(id));
        return "book/book_info";
    }
}
