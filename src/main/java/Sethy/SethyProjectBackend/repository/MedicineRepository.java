package Sethy.SethyProjectBackend.repository;

import Sethy.SethyProjectBackend.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    Medicine getByMedicineName(String medicineName);
}
