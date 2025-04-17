package Hospital_Integration.Hospital_System.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;

@Service
public class DoctorService {
	private final DoctorRepo doctorRepo;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepo doctorRepo, PasswordEncoder passwordEncoder) {
        this.doctorRepo = doctorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public DoctorModel registerDoctor(String email, String password, String displayName, int hospitalId, String specialization, String securityQuestion1, String securityQuestion2) {

        if (doctorRepo.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        DoctorModel doctor = new DoctorModel(email, encodedPassword, displayName, hospitalId, specialization, securityQuestion1, securityQuestion2);
        return doctorRepo.save(doctor);
    }

    // Method to find a user by email
    public DoctorModel findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

    public boolean loginDoctor(String email, String password) {
        DoctorModel doctor = doctorRepo.findByEmail(email);

        if (doctor == null) {
            return false; // No hospital found with the given email
        }

        return passwordEncoder.matches(password, doctor.getPassword());
    }

}
