package Hospital_Integration.Hospital_System.dto;


public class BedUpdateRequest {
    private int hospitalId;
    private int totalBeds;
    private int occupiedBeds;
    

    public BedUpdateRequest(int hospitalId, int totalBeds, int occupiedBeds) {
		super();
		this.hospitalId = hospitalId;
		this.totalBeds = totalBeds;
		this.occupiedBeds = occupiedBeds;
	}

	// ✅ Required: Getters and Setters (important for JSON deserialization)
    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getOccupiedBeds() {
        return occupiedBeds;
    }

    public void setOccupiedBeds(int occupiedBeds) {
        this.occupiedBeds = occupiedBeds;
    }
}


