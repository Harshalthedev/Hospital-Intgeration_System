package Hospital_Integration.Hospital_System.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import Hospital_Integration.Hospital_System.dto.HospitalDto;
import Hospital_Integration.Hospital_System.services.HospitalService;


@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	private final HospitalService hospitalService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    

    public HospitalController(HospitalService hospitalService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    
    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("hospital-login");
    }
 
    @PostMapping("/signup")
    public RedirectView registerHospital(@ModelAttribute HospitalDto hospitalDto, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.registerHospital(hospitalDto.getEmail(), hospitalDto.getPassword(), hospitalDto.getDisplayName(), hospitalDto.getHospitalId());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/hospital/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/hospital/signup");
        }
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            if (auth.isAuthenticated()) {
                return new RedirectView("/hospital/dashboard");
            }
        } catch (AuthenticationException e) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
        return new RedirectView("/login?error=true");
    }
}
