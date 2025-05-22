package Hospital_Integration.Hospital_System.controller;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import Hospital_Integration.Hospital_System.dto.UserDto;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.model.UserModel;
import Hospital_Integration.Hospital_System.repository.UserRepo;
import Hospital_Integration.Hospital_System.services.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping
public class UserController {


    private final UserService userService;
    private UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
 

    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/reset-password")
    public ModelAndView showResetPage() {
        return new ModelAndView("resetpassword");
    }

    @GetMapping("/security-question")
    public ModelAndView showSecurityQuestionPage(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("redirect:/reset-password"); // Redirect if email not found
        }
        ModelAndView modelAndView = new ModelAndView("securityquestion");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @GetMapping("/update-password")
    public ModelAndView showUpdatePasswordPage(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return new ModelAndView("redirect:/reset-password"); // Redirect if email not found
        }
        ModelAndView modelAndView = new ModelAndView("updatepassword");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView showDashboardPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String displayUserName = userService.findByEmail(authentication.getName()).getDisplayName();

        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("displayUserName", displayUserName);
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/signup")
    public RedirectView registerUser(@ModelAttribute UserDto userDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(userDTO.getEmail(), userDTO.getPassword(), userDTO.getDisplayName(), userDTO.getSecurityQuestion1(), userDTO.getSecurityQuestion2());
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");
            return new RedirectView("/login");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new RedirectView("/signup");
        }
    }

//    @PostMapping("/login")
//    public RedirectView login(@RequestParam String username, @RequestParam String password) {
//        try {
//            Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            if (auth.isAuthenticated()) {
//                return new RedirectView("/dashboard");
//            }
//        } catch (AuthenticationException e) {
//        	
//            RedirectView redirectView = new RedirectView("/login");
//            redirectView.addStaticAttribute("error", true);
//            return redirectView;
//        }
//        return new RedirectView("/login?error=true");
//    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            if (auth.isAuthenticated()) {
                return new RedirectView("/dashboard");
            }
        } catch (AuthenticationException e) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.addStaticAttribute("error", true);
            return redirectView;
        }
        return new RedirectView("/login?error=true");
    }

    @PostMapping("/reset-password")
    public RedirectView requestPasswordReset(@RequestParam String email, RedirectAttributes redirectAttributes, HttpSession session) {
        if (userService.findByEmail(email)!=null) {
            session.setAttribute("email", email); // Store email in session
            redirectAttributes.addFlashAttribute("infoMessage", "Please answer the security questions to reset your password.");
            return new RedirectView("/security-question");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Email not found.");
            return new RedirectView("/reset-password");
        }
    }

    @PostMapping("/security-question")
    public RedirectView verifySecurityQuestions(@RequestParam String securityAnswer1,
                                                 @RequestParam String securityAnswer2,
                                                 HttpSession session,
                                                 RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email"); // Get email from session
        if (email != null && userService.verifySecurityAnswers(email, securityAnswer1, securityAnswer2)) {
            return new RedirectView("/update-password");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Security answers are incorrect.");
            return new RedirectView("/security-question");
        }
    }

    @PostMapping("/update-password")
    public RedirectView updatePassword(@RequestParam String newPassword,
                                       @RequestParam String confirmPassword,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email"); // Get email from session
        if (email == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Session expired. Please try resetting your password again.");
            return new RedirectView("/reset-password");
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match.");
            return new RedirectView("/update-password");
        }

        userService.updatePassword(email, newPassword);
        redirectAttributes.addFlashAttribute("successMessage", "Password updated successfully! Please log in.");

        // Clear the email from the session after use
        session.removeAttribute("email");

        return new RedirectView("/login");
    }
    
    @PostMapping("/profile")
    public RedirectView updateProfile(@RequestParam String displayName,
            @RequestParam String oldPassword,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String securityQuestion1,
            @RequestParam String securityQuestion2,
            RedirectAttributes redirectAttributes) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userEmail = authentication.getName();
			UserModel user = userService.findByEmail(userEmail);
			if (user == null) {
		        throw new UsernameNotFoundException("User not found with email: " + userEmail);
		    }
			
			String savedPassword = user.getPassword();
			
			// Check if the old password is correct
			if (!passwordEncoder.matches(oldPassword, savedPassword)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Old password is incorrect.");
			return new RedirectView("/dashboard");
			}
			
			// Check if new password and confirm password match
			if (!password.equals(confirmPassword)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match.");
			return new RedirectView("/dashboard");
			}
			
			// Update user details
			user.setDisplayName(displayName);
			user.setSecurityQuestion1(securityQuestion1);
			user.setSecurityQuestion2(securityQuestion2);
			user.setPassword(passwordEncoder.encode(password));
			
			userService.updateUser(user);
			
			redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully.");
			return new RedirectView("/dashboard");
	}
}