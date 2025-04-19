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
    
    public HospitalModel findByHospitalId(int hospitalId) {
    	System.out.print(hospitalRepo.findByHospitalId(hospitalId));
    	return hospitalRepo.findByHospitalId(hospitalId);
    }

    // Method to find a user by email
    public HospitalModel findByEmail(String email) {
        return hospitalRepo.findByEmail(email);
    }

    public boolean loginHospital(String email, String password) {
        HospitalModel hospital = hospitalRepo.findByEmail(email);

        if (hospital == null) {
            return false; // No hospital found with the given email
        }

        return passwordEncoder.matches(password, hospital.getPassword());
    }

}
