package kr.ac.daelim.bbr.web.admin;

import kr.ac.daelim.bbr.service.BookService;
import kr.ac.daelim.bbr.service.RegistrationService;
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
    private final BookService bookService;

    @GetMapping("/registrations")
    public String registrations(Model model) {
        log.info("[GET /admin/registrations]");
        model.addAttribute("list", registrationService.findAllDesc());

        return "admin/registrations_list";
    }

    @PostMapping("/{id}/comp")
    public String registrationComp(@PathVariable Long id) {
        registrationService.complete(id);
        bookService.addStock(id);

        return "redirect:/admin/registrations";
    }

    @PostMapping("/{id}/cancel")
    public String registrationCancel(@PathVariable Long id) {
        registrationService.cancel(id);

        return "redirect:/admin/registrations";
    }
}
