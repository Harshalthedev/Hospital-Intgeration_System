package Hospital_Integration.Hospital_System.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import Hospital_Integration.Hospital_System.dto.BedUpdateRequest;
import Hospital_Integration.Hospital_System.dto.HospitalDto;
import Hospital_Integration.Hospital_System.model.BedModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.BedRepo;
import Hospital_Integration.Hospital_System.repository.BedService;
import Hospital_Integration.Hospital_System.services.BedServiceImpl;
import Hospital_Integration.Hospital_System.services.HospitalService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;
    private final PasswordEncoder passwordEncoder;
    private final BedService bedService; // ✅ Inject BedService
    private final BedRepo bedRepo;

    public HospitalController(HospitalService hospitalService,
                              PasswordEncoder passwordEncoder,
                              BedServiceImpl bedService,
                              BedRepo bedRepo) {
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
        this.bedService = bedService;
        this.bedRepo = bedRepo;// ✅ Initialize it
    }

    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("hospital-signup");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("hospital-login");
    }
    
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Clear the session
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully.");
        return new RedirectView("/hospital/login");
    }


    @PostMapping("/signup")
    public RedirectView registerHospital(@ModelAttribute HospitalDto hospitalDto, RedirectAttributes redirectAttributes) {
        try {
            hospitalService.registerHospital(hospitalDto.getEmail(), hospitalDto.getPassword(),
                    hospitalDto.getDisplayName(), hospitalDto.gethospitalId(), hospitalDto.getAddress());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/hospital/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/hospital/signup");
        }
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {
        try {
            HospitalModel hospital = hospitalService.loginHospital(username, password);
            session.setAttribute("hospitalId", hospital.getHospitalId());
            return new RedirectView("/hospital/dashboard");
        } catch (AuthenticationException | NullPointerException e) {
            RedirectView redirectView = new RedirectView("/hospital/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {
        String hospitalId = String.valueOf(session.getAttribute("hospitalId"));
        HospitalModel hospital = hospitalService.findByHospitalId(Integer.parseInt(hospitalId));

        ModelAndView modelAndView = new ModelAndView("hospital-dashboard");
        modelAndView.addObject("hospitalId", hospitalId);
        modelAndView.addObject("hospitalName", hospital.getDisplayName());
        modelAndView.addObject("hospitalAddress", hospital.getAddress()); // Add this line
        return modelAndView;
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
        mav.addObject("hospitalId", hospitalId);

        return mav;
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


    @GetMapping("/update-beds/{hospitalId}/{totalBeds}/{occupiedBeds}")
    public ResponseEntity<String> updateBeds(@PathVariable int hospitalId, @PathVariable int totalBeds, @PathVariable int occupiedBeds) {
    	System.out.print("hello i am in controller");
    	try {
    		BedUpdateRequest request = new BedUpdateRequest(hospitalId, totalBeds, occupiedBeds);
            bedService.updateBeds(request.getHospitalId(), request.getTotalBeds(), request.getOccupiedBeds());
            return ResponseEntity.ok("Bed count updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update beds: " + e.getMessage());
        }
    }
    
    @GetMapping("/findById/{hospitalId}")
    public ResponseEntity<?> getBedDetails(@PathVariable int hospitalId) {
        BedModel bed = bedRepo.findByHospitalId(hospitalId);

        if (bed == null) {
            return ResponseEntity
                    .status(404)
                    .body("No bed information found for hospital ID: " + hospitalId);
        }

        return ResponseEntity.ok(bed);
    }
    

}
