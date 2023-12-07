package jackynote.pro.controller;

import jackynote.pro.model.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }
        return "login";
    }

    @GetMapping("/verify-otp")
    public String verifyOtpPage(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }
        return "otp";
    }

    @PostMapping("/verify-otp")
    public String processVerifyOtp(@RequestParam(value = "otp") String otp) {
        if ("123456".equals(otp)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            authUser.setVerify2Fa(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "redirect:/";
    }
}

