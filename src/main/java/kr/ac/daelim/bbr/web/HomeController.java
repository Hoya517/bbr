package kr.ac.daelim.bbr.web;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BookService bookService;

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model) {
        log.info("[ GET / ]");
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
//        log.info("thumbnail: {} & storeFileName: {}", bookService.findAllDesc().get(0).getThumbnail(), bookService.findAllDesc().get(0).getAttachFile().getStoreFileName());
//        model.addAttribute("books", bookService.findAllDesc());

        return "home/index";
    }

    @GetMapping("/major")
    public String major() {
        return "home/major";
    }

    @GetMapping("/department")
    public String department() {
        return "home/department";
    }

    @GetMapping("/non-major")
    public String nonMajor() {
        return "home/non-major";
    }

    @GetMapping("/new")
    public String nevv() {
        return "home/new";
    }

    @GetMapping("/rank")
    public String rank() {
        return "home/rank";
    }
}
