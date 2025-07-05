package Hospital_Integration.Hospital_System.services;

import java.util.Date;
import java.util.List;

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
			int hospitalId, Date appointmentDate) {
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
		
		appointmentRepo.save(appointmentModel);
	}

	public List<AppointmentModel> findByUserEmail(String email) {
	    return appointmentRepo.findByUserEmail(email);
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


}
