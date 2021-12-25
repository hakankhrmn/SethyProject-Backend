package Sethy.SethyProjectBackend.repository;

import Sethy.SethyProjectBackend.model.Pharmacist;
import Sethy.SethyProjectBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist,Integer> {
    Pharmacist getPharmacistByUser(User user);
}
