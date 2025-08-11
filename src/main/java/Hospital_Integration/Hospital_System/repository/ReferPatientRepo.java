package Hospital_Integration.Hospital_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hospital_Integration.Hospital_System.model.ReferEmergencyPatient;

@Repository
public interface ReferPatientRepo extends JpaRepository<ReferEmergencyPatient, Long> {
	ReferEmergencyPatient findById(int patientId);
    List<ReferEmergencyPatient> findByHospitalIdAndStatus(int hospitalId, int status);
}
