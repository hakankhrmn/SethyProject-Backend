package Sethy.SethyProjectBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @NotNull(message = "Name field is mandatory")
    @Column(name="user_name")
    private String userName;

    @NotNull(message = "Name field is mandatory")
    @Column(name="user_surname")
    private String userSurname;

    @Column(name="user_mail")
    @Email(message = "Email is not valid")
    private String userMail;

    @Column(name="user_password")
    private String userPassword;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
    private Pharmacist pharmacist;
}
