package Hospital_Integration.Hospital_System.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import Hospital_Integration.Hospital_System.dto.HospitalDto;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.HospitalRepo;
import Hospital_Integration.Hospital_System.services.HospitalService;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	private final HospitalService hospitalService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public HospitalController(HospitalService hospitalService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.hospitalService = hospitalService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("hospital-signup");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("hospital-login");
    }
    
    @GetMapping("/details/{hospitalId}")
    public ModelAndView getHospitalDashboard(@PathVariable String hospitalId, HttpSession session) {
        HospitalModel hospital = hospitalService.findByHospitalId(Integer.parseInt(hospitalId));
        ModelAndView mav = new ModelAndView("user-hospitalDashBoard"); 
        
        mav.addObject("hospital", hospital);
        mav.addObject("displayUserName", session.getAttribute("displayUserName"));
        mav.addObject("userEmail", session.getAttribute("userEmail"));
        mav.addObject("userAge", session.getAttribute("userAge"));
        mav.addObject("userGender", session.getAttribute("userGender"));
        
        return mav;
    }
    
    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {
        String hospitalId = String.valueOf(session.getAttribute("hospitalId"));
        ModelAndView modelAndView = new ModelAndView("hospital-dashboard");
        modelAndView.addObject("hospitalId", hospitalId);

        return modelAndView;
    }
    
    @GetMapping("/available-hospitals")
    public ResponseEntity<List<HospitalModel>> getRoomsForCurrentUser() {
        try {
            List<HospitalModel> hospitals = hospitalService.getAllAvailableHospitals();
            return ResponseEntity.ok(hospitals); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/signup")
    public RedirectView registerHospital(@ModelAttribute HospitalDto hospitalDto, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.registerHospital(hospitalDto.getEmail(), hospitalDto.getPassword(), hospitalDto.getDisplayName(), hospitalDto.gethospitalId());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/hospital/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/hospital/signup");
        }
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        try {
            HospitalModel hospital = hospitalService.loginHospital(username, password); 
            session.setAttribute("hospitalId", hospital.getHospitalId());

            return new RedirectView("/hospital/dashboard");

        } catch (AuthenticationException e) {
            RedirectView redirectView = new RedirectView("/hospital/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
    }

   
}