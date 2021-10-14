package kr.ac.daelim.bbr.web.admin;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberType;
import kr.ac.daelim.bbr.service.OrderService;
import kr.ac.daelim.bbr.service.RegistrationService;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final RegistrationService registrationService;
    private final OrderService orderService;

    @GetMapping("/registrations")
    public String registrations(@Login Member loginMember, Model model) {
        log.info("[GET /admin/registrations]");
        if (loginMember.getMemberType() == MemberType.ADMIN) {
            model.addAttribute("member", loginMember);
            model.addAttribute("list", registrationService.findAllDesc());
        }

        return "admin/registrations";
    }

    @PostMapping("/registrations/{id}/comp")
    public String registrationComp(@PathVariable Long id) {
        registrationService.complete(id);

        return "redirect:/admin/registrations";
    }

    @PostMapping("/registrations/{id}/cancel")
    public String registrationCancel(@PathVariable Long id) {
        registrationService.cancel(id);

        return "redirect:/admin/registrations";
    }

    @GetMapping("/orders")
    public String orders(@Login Member loginMember, Model model) {
        log.info("[GET /admin/orders]");
        if (loginMember.getMemberType() == MemberType.ADMIN) {
            model.addAttribute("member", loginMember);
            model.addAttribute("list", orderService.findAllDesc());

        }

        return "admin/orders";
    }

    @PostMapping("/orders/{id}/comp")
    public String ordersComp(@PathVariable Long id) {
        orderService.completeOrder(id);

        return "redirect:/admin/orders";
    }

    @PostMapping("/orders/{id}/cancel")
    public String ordersCancel(@PathVariable Long id) {
        orderService.cancelOrder(id);

        return "redirect:/admin/orders";
    }
}
