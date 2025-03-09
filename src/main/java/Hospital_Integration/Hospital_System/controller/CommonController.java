package Hospital_Integration.Hospital_System.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping
public class CommonController {
	@GetMapping("/index")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index");
    }
	
	@GetMapping("/doctor-login")
    public ModelAndView showDoctorLoginPage() {
        return new ModelAndView("doctor-login");
    }
	
	@GetMapping("/user-login")
    public ModelAndView showUserLoginPage() {
        return new ModelAndView("user-login");
    }
	
	@GetMapping("/hospital-login")
    public ModelAndView showHospitalLoginPage() {
        return new ModelAndView("hospital-login");
    }
}
