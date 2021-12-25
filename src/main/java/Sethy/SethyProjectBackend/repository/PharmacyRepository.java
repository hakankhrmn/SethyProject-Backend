package Sethy.SethyProjectBackend.repository;

import Sethy.SethyProjectBackend.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Integer> {
    Pharmacy getByLocationLatitudeAndLocationLongitude(double locationLatitude, double locationLongitude);
    Pharmacy getByPharmacyId(int pharmacyId);
}
