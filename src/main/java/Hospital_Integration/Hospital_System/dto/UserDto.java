package Hospital_Integration.Hospital_System.dto;

import java.util.Date;

public class UserDto {
    private String email;
    private String password;
    private String displayName;
    private String securityQuestion1;
    private String securityQuestion2;
	private int age;
    private String gender;
	private Date time;
    private String Disease;
    private String LastVisitedHospital;
    private String LastVisitedDocotr;    
    private String Remark;
   
	// Getters and Setters
    
    public String getEmail() { return email; }
    public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDisease() {
		return Disease;
	}
	public void setDisease(String disease) {
		Disease = disease;
	}
	public String getLastVisitedHospital() {
		return LastVisitedHospital;
	}
	public void setLastVisitedHospital(String lastVisitedHospital) {
		LastVisitedHospital = lastVisitedHospital;
	}
	public String getLastVisitedDocotr() {
		return LastVisitedDocotr;
	}
	public void setLastVisitedDocotr(String lastVisitedDocotr) {
		LastVisitedDocotr = lastVisitedDocotr;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
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
    
	public UserDto(String email, String password, String displayName, int age, String gender, Date time, String disease,
			String lastVisitedHospital, String lastVisitedDocotr, String remark) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.age = age;
		this.gender = gender;
		this.time = time;
		Disease = disease;
		LastVisitedHospital = lastVisitedHospital;
		LastVisitedDocotr = lastVisitedDocotr;
		Remark = remark;
	}
	public UserDto(String email, String password, String displayName) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
	}
	public UserDto(String email, String password, String displayName, String securityQuestion1,
			String securityQuestion2, int age, String gender) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.securityQuestion1 = securityQuestion1;
		this.securityQuestion2 = securityQuestion2;
		this.age = age;
		this.gender = gender;
	}
	public UserDto() {
		super();
	} 
	
	
}
