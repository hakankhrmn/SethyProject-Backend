package Sethy.SethyProjectBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pharmacists")
public class Pharmacist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pharmacist_id")
    private int pharmacistId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pharmacist_user",
            joinColumns = @JoinColumn(name = "pharmacist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pharmacyOwner")
    private Pharmacy pharmacy;
}
