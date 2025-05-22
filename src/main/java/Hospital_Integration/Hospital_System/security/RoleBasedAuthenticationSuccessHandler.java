package Hospital_Integration.Hospital_System.security;

import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals("DOCTOR")) {
                response.sendRedirect("/doctor/dashboard");
                return;
            } else if (role.equals("HOSPITAL")) {
                response.sendRedirect("/hospital/dashboard");
                return;
            } else if (role.equals("USER")) {
                response.sendRedirect("/dashboard");
                return;
            }
        }

        response.sendRedirect("/login?error");
    }
}

