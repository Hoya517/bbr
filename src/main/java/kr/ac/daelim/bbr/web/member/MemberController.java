package kr.ac.daelim.bbr.web.member;

import kr.ac.daelim.bbr.service.MemberService;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberSaveRequestDto") MemberSaveRequestDto memberSaveRequestDto) {
        return "members/joinForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute MemberSaveRequestDto memberSaveRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/joinForm";
        }

        memberService.save(memberSaveRequestDto);
        return "redirect:/login";
    }
}
