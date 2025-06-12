package Hospital_Integration.Hospital_System.repository;


public interface BedService {
    void updateBeds(int hospitalId, int totalBeds, int occupiedBeds);
}

