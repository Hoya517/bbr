package kr.ac.daelim.bbr.web.member;

import kr.ac.daelim.bbr.service.EmailService;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.service.MemberService;
import kr.ac.daelim.bbr.service.OrderService;
import kr.ac.daelim.bbr.service.RegistrationService;
import kr.ac.daelim.bbr.utils.session.SessionConst;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import kr.ac.daelim.bbr.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;
    private final RegistrationService registrationService;
    private final OrderService orderService;


    /* 회원가입 */
    @GetMapping("/add")
    public String addForm(@Login Member loginMember, @ModelAttribute("memberSaveRequestDto") MemberSaveRequestDto memberSaveRequestDto) {
        if (loginMember != null) {
            return "redirect:/";
        }
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
    @PostMapping("/sendMail")
    @ResponseBody
    public String emailConfirm(String email) throws Exception {
        try {
            emailService.sendSimpleMessage(email + "@email.daelim.ac.kr");
        } catch (AddressException e) {
            return "fail";
        }
        return email+"@email.daelim.ac.kr";
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(String code) {
        String result = "fail";
        if(EmailService.ePw.equals(code)) {
            result = "success";
        }
        return result;
    }

    /* 주문 */
    @PostMapping("/order/{id}")
    public String order(@Login Member loginMember, @PathVariable Long id, @RequestParam("count") int count, HttpServletRequest request) {
        log.info("loginMember={}",loginMember.getName());
        orderService.order(loginMember.getId(), id, count);

        Member member = memberService.findById(loginMember.getId());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/members/myPage/orders";
    }

    /* 마이페이지 */
    @GetMapping("/myPage")
    public String myPage(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "members/myPage";
    }

    @GetMapping("/myPage/privacy")
    public String privacy(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "members/privacy";
    }

    @GetMapping("/myPage/profile")
    public String profile(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
        }
        return "members/profile";
    }

    @GetMapping("/myPage/registrations")
    public String registrations(@Login Member loginMember, Model model, HttpServletRequest request) {
        if (loginMember != null) {
            Member member = memberService.findById(loginMember.getId());
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, member);

            model.addAttribute("member", member);
            model.addAttribute("list", registrationService.findAllDesc(loginMember));
        }
        return "members/registrations";
    }

    @PostMapping("/myPage/registrations/{id}/cancel")
    public String cancelRegistration(@PathVariable Long id) {
        registrationService.cancel(id);

        return "redirect:/members/myPage/registrations";
    }

    @GetMapping("/myPage/orders")
    public String orders(@Login Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("member", loginMember);
            model.addAttribute("list", orderService.findAllDesc(loginMember));
        }
        return "members/orders";
    }

    @PostMapping("/myPage/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id, @Login Member loginMember, HttpServletRequest request) {
        orderService.cancelOrder(id);

        Member member = memberService.findById(loginMember.getId());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/members/myPage/orders";
    }
}
