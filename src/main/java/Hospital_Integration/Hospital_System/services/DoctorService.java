package Hospital_Integration.Hospital_System.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
import Hospital_Integration.Hospital_System.repository.HospitalRepo;

@Service
public class DoctorService {
	private final DoctorRepo doctorRepo;
	private final HospitalService hospitalService;
//	private final HospitalRepo hospitalRepo;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepo doctorRepo,HospitalService hospitalService, PasswordEncoder passwordEncoder) {
        this.doctorRepo = doctorRepo;
//        this.hospitalRepo = hospitalRepo;
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public DoctorModel registerDoctor(String email, String password, String displayName, int hospitalId, String specialization) {
        System.out.println("Hello iam in hospital register");
    	System.out.print(doctorRepo.findByEmail(email));
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

        DoctorModel doctor = new DoctorModel(email, encodedPassword, displayName, hospitalId, specialization);
        return doctorRepo.save(doctor);
    }

    // Method to find a user by email
    public DoctorModel findByEmail(String email) {
        return doctorRepo.findByEmail(email);
    }

    public boolean loginDoctor(String email, String password, int hospitalId) {
        DoctorModel doctor = doctorRepo.findByEmail(email);

        if (doctor == null) {
            return false; // No hospital found with the given email
        }
        HospitalModel hospital = hospitalService.findByHospitalId(hospitalId);
        if (hospital == null) return false;

        return passwordEncoder.matches(password, doctor.getPassword());
    }

}
