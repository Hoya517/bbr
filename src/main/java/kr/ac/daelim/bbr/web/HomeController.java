package kr.ac.daelim.bbr.web;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "home/index";
    }

    @GetMapping("/search")
    public String search(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/search";
    }

    @GetMapping("/major")
    public String major(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/major";
    }

    @GetMapping("/department")
    public String department(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/department";
    }

    @GetMapping("/non-major")
    public String nonMajor(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/non-major";
    }

    @GetMapping("/new")
    public String nevv(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/new";
    }

    @GetMapping("/rank")
    public String rank(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "home/rank";
    }
}
