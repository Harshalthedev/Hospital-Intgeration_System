package Hospital_Integration.Hospital_System.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.model.ReferEmergencyPatient;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
import Hospital_Integration.Hospital_System.repository.HospitalRepo;
import Hospital_Integration.Hospital_System.repository.ReferPatientRepo;

@Service
public class HospitalService {
	private final HospitalRepo hospitalRepo;
	private final ReferPatientRepo referPatientRepo;
    private final PasswordEncoder passwordEncoder;

    public HospitalService(HospitalRepo hospitalRepo, ReferPatientRepo referPatientRepo, PasswordEncoder passwordEncoder) {
		super();
        this.referPatientRepo = referPatientRepo;
    	this.hospitalRepo = hospitalRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public HospitalModel registerHospital(String email, String password, String displayName, int hospitalId, String address, Long phoneNumber) {
        if (hospitalRepo.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        HospitalModel hospital = new HospitalModel(email, encodedPassword, displayName, hospitalId, address, phoneNumber);
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
    
    
    public List<ReferEmergencyPatient> fetchEmergencyPatient(int hospitalId) {
    	return referPatientRepo.findByHospitalIdAndStatus(hospitalId, 0);
    }
    
    public List<HospitalModel> getAllAvailableHospitals() {
        List<HospitalModel> hospitals = hospitalRepo.findAll();
        return hospitals;
    }

	public void referPatient(int hospitalId, int age, int status, String name, String gender, String email, String disease) {
		ReferEmergencyPatient patient = new ReferEmergencyPatient(hospitalId, age, status, name, gender, email, disease);
		referPatientRepo.save(patient);
	}
}
