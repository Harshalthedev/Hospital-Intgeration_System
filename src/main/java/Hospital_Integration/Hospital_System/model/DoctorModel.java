package Hospital_Integration.Hospital_System.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "display_name", nullable = false)
    private String displayName;
    
    @Column(unique = true, nullable = false)
    private int hospitalId;
    
    @Column(unique = true, nullable = false)
    private String specialization;
    
    @Column(name = "SQ1", nullable = false)
    private String securityQuestion1;
    
    @Column(name = "SQ2", nullable = false)
    private String securityQuestion2;

	public DoctorModel() {}
	
	public DoctorModel( String email, String password, String displayName, int hospitalId, String specialization, String securityQuestion1, String securityQuestion2) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.hospitalId = hospitalId;
        this.specialization = specialization;
        this.securityQuestion2 = securityQuestion2;
        this.securityQuestion1 = securityQuestion1;
	}
	
    public DoctorModel(String email, String password, int hositalId, String specialization) {
        this.email = email;
        this.password = password;
        this.hospitalId = hospitalId;
        this.specialization = specialization;
    }


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

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	
    public String getSpecialization() {
    	return specialization;
    }
    
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
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
    
}

