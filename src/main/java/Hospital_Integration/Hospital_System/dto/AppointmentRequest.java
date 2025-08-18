package Hospital_Integration.Hospital_System.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AppointmentRequest {
    public String userName;
    public String userEmail;
    public String userGender;
    public int userAge;
    public String userDisease;
    public String doctorName;
    public int hospitalId;
	public int doctorId;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Date appointmentDate;

	public AppointmentRequest(String userName, String userEmail, String userGender, int userAge, String userDisease,
			String doctorName, int hospitalId, Date appointmentDate, int doctorId) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userAge = userAge;
		this.userDisease = userDisease;
		this.doctorName = doctorName;
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

