package kr.ac.daelim.bbr.web.member;

import kr.ac.daelim.bbr.service.EmailService;
import kr.ac.daelim.bbr.service.MemberService;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberSaveRequestDto") MemberSaveRequestDto memberSaveRequestDto) {
        return "members/joinForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute MemberSaveRequestDto memberSaveRequestDto, BindingResult bindingResult) {
        //TODO 이메일인증 false
        if (bindingResult.hasErrors() || !memberSaveRequestDto.getPersonalInfoTermYn().booleanValue() || !memberSaveRequestDto.getServiceTermYn().booleanValue()) {
            return "members/joinForm";
        }

        memberService.save(memberSaveRequestDto);
        return "redirect:/login";
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public void emailConfirm(String email) throws Exception {
        emailService.sendSimpleMessage(email+"@email.daelim.ac.kr");
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(String code) {
        String result = "false";
        if(EmailService.ePw.equals(code)) {
            result = "true";
        }
        return result;
    }
}
