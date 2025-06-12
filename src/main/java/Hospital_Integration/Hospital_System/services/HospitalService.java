package Hospital_Integration.Hospital_System.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
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
    public HospitalModel registerHospital(String email, String password, String displayName, int hospitalId, String address) {
        if (hospitalRepo.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        HospitalModel hospital = new HospitalModel(email, encodedPassword, displayName, hospitalId, address);
        return hospitalRepo.save(hospital);
    }
    
    public HospitalModel findByHospitalId(int hospitalId) {
    	return hospitalRepo.findByHospitalId(hospitalId);
    }

    public HospitalModel loginHospital(String email, String password) {
        HospitalModel hospital = hospitalRepo.findByEmail(email);

        if (hospital == null) {
            return null; 
        }
        return passwordEncoder.matches(password, hospital.getPassword())? hospital:null;
    }
    
    // Method to find a user by email
    public HospitalModel findByEmail(String email) {
        return hospitalRepo.findByEmail(email);
    }


    
    public List<HospitalModel> getAllAvailableHospitals() {
        List<HospitalModel> hospitals = hospitalRepo.findAll();
        return hospitals;
    }
}
