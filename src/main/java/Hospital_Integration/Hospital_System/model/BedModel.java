package Hospital_Integration.Hospital_System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "beds")
public class BedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hospitalId;
    private int totalBeds;
    private int occupiedBeds;

    public BedModel() {}

    public BedModel(int hospitalId, int totalBeds, int occupiedBeds) {
        this.hospitalId = hospitalId;
        this.totalBeds = totalBeds;
        this.occupiedBeds = occupiedBeds;
    }

    // Getters and setters
    public Long getId() { return id; }

    public int getHospitalId() { return hospitalId; }

    public void setHospitalId(int hospitalId) { this.hospitalId = hospitalId; }

    public int getTotalBeds() { return totalBeds; }

    public void setTotalBeds(int totalBeds) { this.totalBeds = totalBeds; }

    public int getOccupiedBeds() { return occupiedBeds; }

    public void setOccupiedBeds(int occupiedBeds) { this.occupiedBeds = occupiedBeds; }
}
