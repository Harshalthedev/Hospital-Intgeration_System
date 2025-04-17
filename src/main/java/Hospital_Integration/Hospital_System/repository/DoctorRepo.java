package Hospital_Integration.Hospital_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Hospital_Integration.Hospital_System.model.DoctorModel;


public interface DoctorRepo extends JpaRepository<DoctorModel, Long>{
	DoctorModel findByEmail(String email);
}
