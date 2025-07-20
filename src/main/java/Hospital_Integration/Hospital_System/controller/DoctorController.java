package Hospital_Integration.Hospital_System.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import Hospital_Integration.Hospital_System.dto.DoctorDto;
import Hospital_Integration.Hospital_System.model.AppointmentModel;
import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
import Hospital_Integration.Hospital_System.services.AppointmentService;
import Hospital_Integration.Hospital_System.services.DoctorService;
import Hospital_Integration.Hospital_System.services.HospitalService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
    @SuppressWarnings("unused")
	private final PasswordEncoder passwordEncoder;
    private final AppointmentService appointmentService;
	private final DoctorService doctorService;
	private final DoctorRepo doctorRepo;
	private final HospitalService hospitalService; 

    public DoctorController(DoctorRepo doctorRepo,DoctorService doctorService, HospitalService hospitalService, PasswordEncoder passwordEncoder, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.doctorRepo = doctorRepo;
        this.hospitalService = hospitalService;
		this.passwordEncoder = passwordEncoder;
		this.appointmentService = appointmentService;
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
    public ModelAndView showDashboardPage(HttpSession session) {
        String hospitalName = String.valueOf(session.getAttribute("hospitalName"));
        String doctorName = String.valueOf(session.getAttribute("doctorName"));
        String hospitalId = String.valueOf(session.getAttribute("hospitalId"));
        String ID = String.valueOf(session.getAttribute("ID"));
        
        ModelAndView modelAndView = new ModelAndView("doctor-dashboard");
        modelAndView.addObject("doctorName", doctorName);
        modelAndView.addObject("hospitalName", hospitalName);
        modelAndView.addObject("hospitalId", hospitalId);
        modelAndView.addObject("ID", ID);

        return modelAndView;
    }
 
    @PostMapping("/signup")
    public RedirectView registerHospital(@ModelAttribute DoctorDto doctorDto, RedirectAttributes redirectAttributes) {
        try {
        	doctorService.registerDoctor(doctorDto.getEmail(), doctorDto.getPassword(), doctorDto.getDisplayName(), doctorDto.gethospitalId(), doctorDto.getSpecialization(), doctorDto.getContact());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/doctor/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/doctor/signup");
        }
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, @RequestParam int hospitalId, HttpSession session) {        
    	try {
    		DoctorModel doctor = doctorService.loginDoctor(username, password, hospitalId);
    		System.out.println("this is doctor id "+doctor.getId());
    		    HospitalModel hospital = hospitalService.findByHospitalId(hospitalId);
    		    session.setAttribute("ID", doctor.getId());
    		    session.setAttribute("hospitalId", doctor.getHospitalId());
                session.setAttribute("hospitalName", hospital.getDisplayName());
                session.setAttribute("doctorName", doctor.getDisplayName());
                return new RedirectView("/doctor/dashboard");
            
        } catch (Exception e) {
            RedirectView redirectView = new RedirectView("/doctor/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
    }
    
    @GetMapping("/findDoctor-ByHospitalId/{hospitalId}")
    public ResponseEntity<List<DoctorModel>> getDoctorByHospitalId(@PathVariable int hospitalId) {
        try {
            List<DoctorModel> doctors = doctorService.findDoctorsByHospitalId(hospitalId);
            return ResponseEntity.ok(doctors); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentModel>> getDoctorAppointments(HttpSession session) {
        String doctorName = (String) session.getAttribute("doctorName");
        int hospitalId = Integer.parseInt(session.getAttribute("hospitalId").toString());

        List<AppointmentModel> appointments = appointmentService.findByDoctorNameAndHospitalId(doctorName, hospitalId);
        return ResponseEntity.ok(appointments);
    }
    
    @PostMapping("/update/{doctorId}")
    public ResponseEntity<String> updateDoctorDetails(
        @PathVariable Long doctorId,
        @RequestBody DoctorDto dto
    ) {
        DoctorModel doctor = doctorRepo.findById(doctorId).orElse(null);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        doctor.setContact(dto.getContact());
        doctor.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            doctor.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        doctorRepo.save(doctor);
        return ResponseEntity.ok("Doctor updated successfully");
    }

    
    @GetMapping("/details/{doctorId}")
    public ResponseEntity<Map<String, String>> getDoctorDetails(@PathVariable Long doctorId) {
        DoctorModel doctor = doctorRepo.findById(doctorId).orElse(null); // FIXED
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, String> data = new HashMap<>();
        data.put("name", doctor.getDisplayName());
        data.put("email", doctor.getEmail());
        data.put("contact", String.valueOf(doctor.getContact()));

        return ResponseEntity.ok(data);
    }

    
    @GetMapping("/appointments/{id}/{status}")
    public ResponseEntity<AppointmentModel> updateAppointmentStatus(@PathVariable Long id, @PathVariable int status) {
        if (status != 0 && status != 1 && status != 2) {
            return ResponseEntity.badRequest().body(null);
        }

        AppointmentModel updatedAppointment = appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(updatedAppointment);
    }
    
    @GetMapping("/appointment/{status}/{doctorName}")
    public List<AppointmentModel> getAppointmentsByDoctorAndStatus(
            @PathVariable String doctorName,
            @PathVariable int status) {
    	
        return appointmentService.findByDoctorNameAndStatus(doctorName, status);
    }
    
    
}