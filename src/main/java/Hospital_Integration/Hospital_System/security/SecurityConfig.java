package Hospital_Integration.Hospital_System.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import Hospital_Integration.Hospital_System.services.CustomUserDetailsService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .authorizeRequests(authorize -> authorize
                .requestMatchers(
                    "/index",
                    "/home",
                    "/doctor-login", 
                    "/login", 
                    "/signup", 
                    "/reset-password", 
                    "/security-question", 
                    "/update-password", 
                    "/hospital/login", 
                    "/hospital/signup",
                    "/doctor/signup",
                    "/doctor/login"  
                ).permitAll()
                .requestMatchers(
                   "/dashboard", 
                   "/hospital/dashboard",	
                    "/doctor/dashboard"
                    ).authenticated()
            )
            .formLogin(form -> form
            	    .loginPage("/login") // or use a shared login page
            	    .successHandler((request, response, authentication) -> {
            	        String role = authentication.getAuthorities().iterator().next().getAuthority();
            	        if (role.equals("ROLE_PATIENT")) {
            	            response.sendRedirect("/dashboard");
            	        } else if (role.equals("ROLE_DOCTOR")) {
            	            response.sendRedirect("/doctor/dashboard");
            	        } else if (role.equals("ROLE_HOSPITAL")) {
            	            response.sendRedirect("/hospital/dashboard");
            	        } else {
            	            response.sendRedirect("/login?error=true");
            	        }
            	    })
            	)

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }
}