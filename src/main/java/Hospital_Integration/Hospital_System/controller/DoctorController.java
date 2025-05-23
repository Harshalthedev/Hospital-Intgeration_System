package Hospital_Integration.Hospital_System.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import Hospital_Integration.Hospital_System.dto.DoctorDto;
import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
import Hospital_Integration.Hospital_System.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
	private final DoctorService doctorService;
//    private final AuthenticationManager authenticationManager;
    

    public DoctorController(DoctorService doctorService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
		this.passwordEncoder = passwordEncoder;
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
    
    @GetMapping("/findDoctor-ByHospitalId/{hospitalId}")
    public ResponseEntity<List<DoctorModel>> getDoctorByHospitalId(@PathVariable int hospitalId) {
        try {
            List<DoctorModel> doctors = doctorService.findDoctorsByHospitalId(hospitalId);
            System.out.println("hello i am from this id "+doctors.toString());
            return ResponseEntity.ok(doctors); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}