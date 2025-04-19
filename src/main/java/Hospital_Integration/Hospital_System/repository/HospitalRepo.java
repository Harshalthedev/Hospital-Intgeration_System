package Hospital_Integration.Hospital_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Hospital_Integration.Hospital_System.model.HospitalModel;

public interface HospitalRepo extends JpaRepository<HospitalModel, Long>{
	HospitalModel findByEmail(String email);
	HospitalModel findByHospitalId(int hospitalId);
}