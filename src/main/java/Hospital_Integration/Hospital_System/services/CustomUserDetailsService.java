package Hospital_Integration.Hospital_System.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.DoctorModel;
import Hospital_Integration.Hospital_System.model.HospitalModel;
import Hospital_Integration.Hospital_System.model.UserModel;
import Hospital_Integration.Hospital_System.repository.DoctorRepo;
import Hospital_Integration.Hospital_System.repository.HospitalRepo;
import Hospital_Integration.Hospital_System.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel patient = userRepo.findByEmail(email);
        if (patient != null) {
            return new org.springframework.security.core.userdetails.User(
                    patient.getEmail(),
                    patient.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_PATIENT"))
            );
        }

        DoctorModel doctor = doctorRepo.findByEmail(email);
        if (doctor != null) {
            return new org.springframework.security.core.userdetails.User(
                    doctor.getEmail(),
                    doctor.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR"))
            );
        }

        HospitalModel hospital = hospitalRepo.findByEmail(email);
        if (hospital != null) {
            return new org.springframework.security.core.userdetails.User(
                    hospital.getEmail(),
                    hospital.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_HOSPITAL"))
            );
        }

        throw new UsernameNotFoundException("No user found with email: " + email);
    }
}

