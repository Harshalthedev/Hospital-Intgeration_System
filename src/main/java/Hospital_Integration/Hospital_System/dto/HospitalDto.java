package Hospital_Integration.Hospital_System.dto;

public class HospitalDto {
    
    private String email;
    private String password;
    private String displayName;
    private int hospitalId;
	
    
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int gethospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		hospitalId = hospitalId;
	}

	public HospitalDto(String email, String password, String displayName, int hospitalId) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.hospitalId = hospitalId;
	} 
 
}
