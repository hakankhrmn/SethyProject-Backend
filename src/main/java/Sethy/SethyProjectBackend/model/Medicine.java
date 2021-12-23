package Sethy.SethyProjectBackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicine_id")
    private int medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "medicine_expire_date")
    private String medicineExpireDate;

    @Column(name = "medicine_Description")
    private String medicineDescription;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "pharmacyMedicines")
    private List<Pharmacy> medicinePharmacies;

}
