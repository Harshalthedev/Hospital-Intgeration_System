package Hospital_Integration.Hospital_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Hospital_Integration.Hospital_System.model.AppointmentModel;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentModel, Long>{
	AppointmentModel findByHospitalId(int hospitalId);
	List<AppointmentModel> findUsersByHospitalId(int hospitalId);
	List<AppointmentModel> findByUserEmail(String userEmail);
    List<AppointmentModel> findByDoctorNameAndHospitalId(String doctorName, int hospitalId);
    List<AppointmentModel> findByDoctorNameAndStatus(String doctorName, int status);

}
