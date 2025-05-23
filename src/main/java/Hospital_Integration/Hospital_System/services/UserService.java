package Hospital_Integration.Hospital_System.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.UserModel;
import Hospital_Integration.Hospital_System.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public UserModel registerUser(String email, String password, String displayName, String securityQuestion1, String securityQuestion2, int age, String gender) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        UserModel user = new UserModel(email, encodedPassword, displayName, securityQuestion1, securityQuestion2, age, gender);
        return userRepository.save(user);
    }

    // Method to find a user by email
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // JPA save() can handle updates on existing entities
    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);  
    }
    
    // Verify the security answers for the user
    public boolean verifySecurityAnswers(String email, String securityAnswer1, String securityAnswer2) {
    	 UserModel userOptional = userRepository.findByEmail(email);
         System.out.println("email: "+email+", securityAnswer1"+securityAnswer1+", securityAnswer2"+securityAnswer2);
         if (userOptional != null) {
             UserModel user = userOptional;
             return user.getSecurityQuestion1().equals(securityAnswer1) && 
                    user.getSecurityQuestion2().equals(securityAnswer2);
         }
         return false;
    }
    
    // Method to update the user's password
    public void updatePassword(String email, String newPassword) {
        UserModel userOptional = userRepository.findByEmail(email);
        
        if (userOptional != null) {
            UserModel user = userOptional;
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
    }
}