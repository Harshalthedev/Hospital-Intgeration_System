package Hospital_Integration.Hospital_System.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.AppointmentModel;
import Hospital_Integration.Hospital_System.repository.AppointmentRepo;

@Service
public class AppointmentService {
	
	private final AppointmentRepo appointmentRepo;
	
	public AppointmentService(AppointmentRepo appointmentRepo) {
		super();
		this.appointmentRepo = appointmentRepo;
	}

	public void submitAppointment(String userName, String userEmail, String userGender, int userAge, String userDisease, String doctorName,
			int hospitalId, Date appointmentDate, int doctorId) {
		AppointmentModel appointmentModel = new AppointmentModel(); 
		appointmentModel.setUserName(userName);
		appointmentModel.setUserEmail(userEmail);
		appointmentModel.setUserGender(userGender);
		appointmentModel.setUserAge(userAge);
		appointmentModel.setUserDisease(userDisease);
		appointmentModel.setDoctorName(doctorName);
		appointmentModel.setHospitalId(hospitalId);
		appointmentModel.setAppointmentDate(appointmentDate);
		appointmentModel.setStatus(0);
		appointmentModel.setDoctorId(doctorId);
		
		appointmentRepo.save(appointmentModel);
	}

	public List<AppointmentModel> findByUserEmail(String email) {
	    return appointmentRepo.findByUserEmail(email);
	}
	
	public List<AppointmentModel> findByDoctorNameAndStatus(String doctorName, int status) {
	    return appointmentRepo.findByDoctorNameAndStatus(doctorName, status);
	}
	
	public List<AppointmentModel> findByDoctorNameAndHospitalId(String doctorName, int hospitalId) {
	    return appointmentRepo.findByDoctorNameAndHospitalId(doctorName, hospitalId);
	}

	public AppointmentModel updateAppointmentStatus(Long id, int status) {
	    AppointmentModel appointment = appointmentRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

	    appointment.setStatus(status);
	    return appointmentRepo.save(appointment);
	}
	
	public ResponseEntity<Map<String, Integer>> countOpdQueue(int doctorId) {
	    // Fetch appointments for this doctor
	    List<AppointmentModel> appointModel = appointmentRepo.findByDoctorId(doctorId);

	    // Filter by status (0 or 1) and count
	    int cnt = (int) appointModel.stream()
	            .filter(a -> a.getStatus() == 1)
	            .count();

	    Map<String, Integer> response = new HashMap<>();
	    response.put("count", cnt);

	    return ResponseEntity.ok(response);
	}

}
