package Hospital_Integration.Hospital_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Hospital_Integration.Hospital_System.model.ReferEmergencyPatient;

public interface ReferPatientRepo extends JpaRepository<ReferEmergencyPatient, Integer> {
	
}
