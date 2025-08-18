package Hospital_Integration.Hospital_System.model;


import jakarta.persistence.*;

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

    @Column(nullable = false)
    private int hospitalId;

    @Column(nullable = false)
    private String specialization;

    @Column(unique = true, nullable = false)
    private Long contact;
        
    
    public DoctorModel() {}
	
	public DoctorModel(String email, String password, String displayName, int hospitalId, String specialization, Long contact) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.hospitalId = hospitalId;
        this.specialization = specialization;
        this.contact = contact;
	}
	
    public DoctorModel(String email, String password, int hospitalId, String specialization) {
        this.email = email;
        this.password = password;
        this.hospitalId = hospitalId;
        this.specialization = specialization;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

	public void setId(Long id) {
        this.id = id;
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

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }
    
}

