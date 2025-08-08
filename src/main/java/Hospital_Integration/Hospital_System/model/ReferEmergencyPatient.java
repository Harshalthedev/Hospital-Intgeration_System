package Hospital_Integration.Hospital_System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "referPatient")
public class ReferEmergencyPatient {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String email;
	private int age;
	private String gender;
	private String disease;
	private int hospitalId;
	private int status;
	public ReferEmergencyPatient() {
	}
	public ReferEmergencyPatient(int hospitalId, String name, int age, String gender, String email, String disease, int status) {
		super();
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.disease = disease;
		this.hospitalId = hospitalId;
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
