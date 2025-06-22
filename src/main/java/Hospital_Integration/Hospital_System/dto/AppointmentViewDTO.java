package Hospital_Integration.Hospital_System.dto;

import java.util.Date;

public class AppointmentViewDTO {
    public String userName;
    public String doctorName;
    public String userDisease;
    public int status; // 0 = Pending, 1 = Assigned
    public Date appointmentDate;

    public AppointmentViewDTO(String userName, String doctorName, String userDisease, int status, Date appointmentDate) {
        this.userName = userName;
        this.doctorName = doctorName;
        this.userDisease = userDisease;
        this.status = status;
        this.appointmentDate = appointmentDate;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getUserDisease() {
		return userDisease;
	}

	public void setUserDisease(String userDisease) {
		this.userDisease = userDisease;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
    
}

