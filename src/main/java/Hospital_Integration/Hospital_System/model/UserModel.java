package Hospital_Integration.Hospital_System.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "display_name", nullable = false)
    private String displayName;
    
    
    
    @Column(name = "SQ1", nullable = false)
    private String securityQuestion1;
    
    @Column(name = "SQ2", nullable = false)
    private String securityQuestion2;
    
    @Column(name  = "age", nullable = false)
    private int age;
    
    @Column(name = "gender", nullable = false)
    private String gender;
    
    @Column(name = "Disease")
    private String Disease;
    
    @Column(name = "LastVisitedHospital", nullable = true)
    private String LastVisitedHospital;
    
    @Column(name = "LastVisitedDoctor", nullable = true)
    private String LastVisitedDocotr;
    
    @Column(name = "Remark", nullable = true)
    private String Remark;
    
    public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "appointment_time", nullable = true)
	private Date time;

    
    public UserModel() {}

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
    

	public UserModel(String email, String displayName, int age, String gender, String disease,
			String lastVisitedHospital, String lastVisitedDocotr, String remark, Date time) {
		super();
		this.email = email;
		this.displayName = displayName;
		this.age = age;
		this.gender = gender;
		Disease = disease;
		LastVisitedHospital = lastVisitedHospital;
		LastVisitedDocotr = lastVisitedDocotr;
		Remark = remark;
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public UserModel(String email, String password, String displayName, String securityQuestion1,
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
    

	// Getters and setters

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
    
}
