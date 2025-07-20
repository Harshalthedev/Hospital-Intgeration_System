package Hospital_Integration.Hospital_System.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;

@Service
public class DoctorService {
	private final DoctorRepo doctorRepo;
	private final HospitalService hospitalService;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepo doctorRepo,HospitalService hospitalService, PasswordEncoder passwordEncoder) {
        this.doctorRepo = doctorRepo;
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public DoctorModel registerDoctor(String email, String password, String displayName, int hospitalId, String specialization, Long contact) {
        if (doctorRepo.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        HospitalModel hospital;
        try {
            hospital = hospitalService.findByHospitalId(hospitalId);
            if (hospital == null) {
                throw new RuntimeException("Hospital not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Wrong Hospital ID");
        }
        
        String encodedPassword = passwordEncoder.encode(password);

        DoctorModel doctor = new DoctorModel(email, encodedPassword, displayName, hospitalId, specialization, contact);
        return doctorRepo.save(doctor);
    }
    
    public DoctorModel loginDoctor(String email, String password, int hospitalId) {
        DoctorModel doctor = doctorRepo.findByEmail(email);

        if (doctor == null) {
            return null; 
        }
        return (passwordEncoder.matches(password, doctor.getPassword()) 
        		&& (doctor.getHospitalId() == hospitalId)) ? doctor : null;
    }
    
    // find doctor by hospitalid
    public List<DoctorModel> findDoctorsByHospitalId(Integer hospitalId) {
    	return doctorRepo.findByHospitalId(hospitalId);
    }

    // Method to find a user by email
    public DoctorModel findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

}
