package kr.ac.daelim.bbr.web;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model) {

        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }

        return "index";
    }

    @GetMapping("/major")
    public String major() {
        return "major/major";
    }
}
