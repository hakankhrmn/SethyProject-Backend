package Sethy.SethyProjectBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="location_id")
    private int locationId;

    @Column(name="location_latitude")
    private double locationLatitude;

    @Column(name="location_longitude")
    private double locationLongitude;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pharmacyLocation")
    private Pharmacy pharmacy;
}
