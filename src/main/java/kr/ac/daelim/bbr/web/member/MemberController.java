package kr.ac.daelim.bbr.web.member;

//import kr.ac.daelim.bbr.service.EmailService;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.MemberService;
import kr.ac.daelim.bbr.service.OrderService;
import kr.ac.daelim.bbr.service.RegistrationService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
//    private final EmailService emailService;
    private final RegistrationService registrationService;
    private final OrderService orderService;


    /* 회원가입 */
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

    /* 이메일 인증 */
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

    /* 주문 */
    @PostMapping("/order/{id}")
    public String order(@Login Member loginMember, @PathVariable Long id, @RequestParam("count") int count) {
        log.info("loginMember={}",loginMember.getName());
        orderService.order(loginMember.getId(), id, count);
        return "redirect:/members/myPage/orders";
    }

    /* 마이페이지 */
    @GetMapping("/myPage")
    public String myPage() {
        return "members/myPage";
    }

    @GetMapping("/myPage/privacy")
    public String privacy() {
        return "members/privacy";
    }

    @GetMapping("/myPage/profile")
    public String profile() {
        return "members/profile";
    }

    @GetMapping("/myPage/registrations")
    public String registrations(@Login Member loginMember, Model model) {
        model.addAttribute("list", registrationService.findAllDesc(loginMember));
        return "members/registrations";
    }

    @PostMapping("/myPage/registrations/{id}/cancel")
    public String cancelRegistration(@PathVariable Long id) {
        registrationService.cancel(id);

        return "redirect:/members/myPage/registrations";
    }

    @GetMapping("/myPage/orders")
    public String orders(@Login Member loginMember, Model model) {
        model.addAttribute("list", orderService.findAllDesc(loginMember));
        return "members/orders";
    }

    @PostMapping("/myPage/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);

        return "redirect:/members/myPage/orders";
    }
}
