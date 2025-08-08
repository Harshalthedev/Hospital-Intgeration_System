package Hospital_Integration.Hospital_System.services;

import org.springframework.stereotype.Service;

import Hospital_Integration.Hospital_System.model.BedModel;
import Hospital_Integration.Hospital_System.repository.BedRepo;
import Hospital_Integration.Hospital_System.repository.BedService;

@Service
public class BedServiceImpl implements BedService {

    private final BedRepo bedRepo;

    public BedServiceImpl(BedRepo bedRepo) {
        this.bedRepo = bedRepo;
    }

    @Override
    public void updateBeds(int hospitalId, int totalBeds, int occupiedBeds) {
        BedModel existingBed = bedRepo.findByHospitalId(hospitalId);
        if (existingBed == null) {
            existingBed = new BedModel(hospitalId, totalBeds, occupiedBeds);
        } else {
            existingBed.setTotalBeds(totalBeds);
            existingBed.setOccupiedBeds(occupiedBeds);
        }

        bedRepo.save(existingBed);
    }
}


