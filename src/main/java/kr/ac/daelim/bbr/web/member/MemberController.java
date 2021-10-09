package kr.ac.daelim.bbr.web.member;

//import kr.ac.daelim.bbr.service.EmailService;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.MemberService;
import kr.ac.daelim.bbr.service.RegistrationService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import kr.ac.daelim.bbr.web.member.dto.RegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
//    private final EmailService emailService;
    private final RegistrationService registrationService;

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

//    @PostMapping("/sendMail")
//    @ResponseBody
//    public void emailConfirm(String email) throws Exception {
//        emailService.sendSimpleMessage(email+"@email.daelim.ac.kr");
//    }
//
//    @PostMapping("/verifyCode")
//    @ResponseBody
//    public String verifyCode(String code) {
//        String result = "false";
//        if(EmailService.ePw.equals(code)) {
//            result = "true";
//        }
//        return result;
//    }

    @GetMapping("/registrations")
    public String registrationList(@Login Member loginMember, Model model) {
        List<RegistrationResponseDto> list = registrationService.findAllDesc(loginMember);
        for (RegistrationResponseDto r : list) {
            log.info("id={}", r.getId());
        }
        model.addAttribute("list", list);
        return "members/registration_list";
    }
}
