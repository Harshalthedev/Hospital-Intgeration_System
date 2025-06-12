package Hospital_Integration.Hospital_System.repository;

import Hospital_Integration.Hospital_System.model.BedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepo extends JpaRepository<BedModel, Long> {
    BedModel findByHospitalId(int hospitalId);
}

