package Hospital_Integration.Hospital_System.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Hospital_Integration.Hospital_System.model.DoctorModel;


public interface DoctorRepo extends JpaRepository<DoctorModel, Integer>{
	DoctorModel findByEmail(String email);
	Optional<DoctorModel> findById(Long id);
	List<DoctorModel> findByHospitalId(Integer hospitalId);
}
