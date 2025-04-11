package Hospital_Integration.Hospital_System.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.HospitalRepo;

@Service
public class HospitalService {
	private final HospitalRepo hospitalRepo;
    private final PasswordEncoder passwordEncoder;

    public HospitalService(HospitalRepo hospitalRepo, PasswordEncoder passwordEncoder) {
        this.hospitalRepo = hospitalRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public HospitalModel registerHospital(String email, String password, String displayName, int hospitalId) {
        if (hospitalRepo.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        HospitalModel hospital = new HospitalModel(email, encodedPassword, displayName, hospitalId);
        return hospitalRepo.save(hospital);
    }

//    // Method to find a user by email
//    public UserModel findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//    
//    // JPA save() can handle updates on existing entities
//    public UserModel updateUser(UserModel user) {
//        return userRepository.save(user);  
//    }
//    
//    // Verify the security answers for the user
//    public boolean verifySecurityAnswers(String email, String securityAnswer1, String securityAnswer2) {
//    	 UserModel userOptional = userRepository.findByEmail(email);
//         System.out.println("email: "+email+", securityAnswer1"+securityAnswer1+", securityAnswer2"+securityAnswer2);
//         if (userOptional != null) {
//             UserModel user = userOptional;
//             return user.getSecurityQuestion1().equals(securityAnswer1) && 
//                    user.getSecurityQuestion2().equals(securityAnswer2);
//         }
//         return false;
//    }
//    
//    // Method to update the user's password
//    public void updatePassword(String email, String newPassword) {
//        UserModel userOptional = userRepository.findByEmail(email);
//        
//        if (userOptional != null) {
//            UserModel user = userOptional;
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userRepository.save(user);
//        }
//    }
}
