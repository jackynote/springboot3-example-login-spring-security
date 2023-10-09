package jackynote.pro.controller;

import jackynote.pro.model.AuthUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping()
    String homePage(Model model) {
        try {
            AuthUser authUser = isLogin() ? (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : null;
            model.addAttribute("currentUser", authUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "home";
    }

    private boolean isLogin() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }
}
