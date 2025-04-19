package Hospital_Integration.Hospital_System.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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

import Hospital_Integration.Hospital_System.dto.DoctorDto;
import Hospital_Integration.Hospital_System.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	private final DoctorService doctorService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
    

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
//		this.passwordEncoder = passwordEncoder;
//		this.authenticationManager = authenticationManager;
    }
    
    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("doctor-signup");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("doctor-login");
    }
    
    @GetMapping("/dashboard")
    public ModelAndView showDashboardPage() {
        return new ModelAndView("doctor-dashboard");
    }
 
    @PostMapping("/signup")
    public RedirectView registerHospital(@ModelAttribute DoctorDto doctorDto, RedirectAttributes redirectAttributes) {
        try {
        	doctorService.registerDoctor(doctorDto.getEmail(), doctorDto.getPassword(), doctorDto.getDisplayName(), doctorDto.gethospitalId(), doctorDto.getSpecialization());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/doctor/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/doctor/signup");
        }
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, int hospitalId) {        

    	try {
            boolean doctorAuth = doctorService.loginDoctor(username, password,hospitalId);

            if (doctorAuth) {
                return new RedirectView("/doctor/dashboard");
            }
            
        } catch (AuthenticationException e) {
            RedirectView redirectView = new RedirectView("/doctor/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
        return new RedirectView("/doctor/login?error=true");
    }
    
   
}