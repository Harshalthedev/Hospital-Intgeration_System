package Hospital_Integration.Hospital_System.dto;

public class UserDto {
    private String email;
    private String password;
    private String displayName;
    private String securityQuestion1;
    private String securityQuestion2;
   
	// Getters and Setters
    public String getEmail() { return email; }
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
    
    
	public UserDto(String email, String password, String displayName) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
	}    
}
