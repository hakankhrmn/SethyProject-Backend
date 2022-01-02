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
@Table(name = "pharmacies")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pharmacy_id")
    private int pharmacyId;

    @Column(name = "pharmacy_name")
    private String pharmacyName;

    @Column(name = "pharmacy_phone")
    private String pharmacyPhone;

    @Column(name="location_latitude")
    private double locationLatitude;

    @Column(name="location_longitude")
    private double locationLongitude;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pharmacy_pharmacist",
            joinColumns = @JoinColumn(name = "pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacist_id")
    )
    private Pharmacist pharmacyOwner;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "pharmacy_medicine",
            joinColumns = @JoinColumn(name = "pharmacy_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> pharmacyMedicines;
}
