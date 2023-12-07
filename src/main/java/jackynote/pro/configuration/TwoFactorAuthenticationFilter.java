package jackynote.pro.configuration;

import jackynote.pro.model.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TwoFactorAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!request.getRequestURI().contains("/verify-otp")
                && authentication != null
                && authentication.isAuthenticated()
                && !hasOtpVerifiedAuthority(authentication)) {
            response.sendRedirect("/verify-otp");
        } else {
            // Continue with the filter chain for other requests
            filterChain.doFilter(request, response);
        }

    }

    private boolean hasOtpVerifiedAuthority(Authentication authentication) {
        // Check if the user has the authority indicating OTP verification
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return authUser.isVerify2Fa();
    }
}
