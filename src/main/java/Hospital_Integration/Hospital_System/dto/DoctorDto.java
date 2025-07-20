	package Hospital_Integration.Hospital_System.dto;

public class DoctorDto {
    private String email;
    private String password;
    private String displayName;
    private String specialization;
    private int hospitalId;
    private Long contact;
    
   
	// Getters and Setters
    
    public String getEmail() { return email; }
    public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getSpecialization() {return specialization;}
	public void setSpecialization(String specialization) {this.specialization = specialization;}
	
	public int gethospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		hospitalId = hospitalId;
	}
    
    
	public DoctorDto(String email, String password, String displayName, int hospitalId, String specialization, Long contact) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.hospitalId = hospitalId;
		this.specialization = specialization;
		this.contact = contact;


	} 
 
}
