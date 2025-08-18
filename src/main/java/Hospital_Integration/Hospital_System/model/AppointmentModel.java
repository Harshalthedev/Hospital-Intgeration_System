package Hospital_Integration.Hospital_System.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class AppointmentModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
	private String userEmail;
    private String userGender;
    private int userAge;
    private String userDisease;
    private String doctorName;
    private int doctorId;
    private int hospitalId;
    private Date appointmentDate;
    private int status;
    
	public AppointmentModel() {
		super();
	}

	
	public AppointmentModel(String userName, String userEmail, String userGender, int userAge, String userDisease, String doctorName,
			int hospitalId, Date appointmentDate, int doctorId) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userAge = userAge;
		this.userDisease = userDisease;
		this.doctorName = doctorName;
		this.hospitalId = hospitalId;
		this.appointmentDate = appointmentDate;
	}


	public AppointmentModel(String userName, String userDisease, String doctorName,
			Date appointmentDate, int status) {
		super();
		this.userName = userName;
		this.userDisease = userDisease;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.status = status;
	}
	

	public int getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserDisease() {
		return userDisease;
	}

	public void setUserDisease(String userDisease) {
		this.userDisease = userDisease;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}	
    
}
