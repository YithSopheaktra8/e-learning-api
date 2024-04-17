package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String address1;

    @Column(columnDefinition = "TEXT")
    private String address2;

    private LocalDate dob;

    @Column(nullable = false)
    private String email;

    private String familyName;

    private String gender;

    private String givenName;

    private Boolean isDeleted;

    private Boolean isVerified;

    @Column(nullable = false,length = 30, unique = true)
    private String nationalIdCard;

    private String password;

    @Column(nullable = false, length = 30)
    private String phoneNumber;

    private String profile;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(nullable = false,unique = true)
    private String uuid;

    @OneToOne
    private Country country;

    @OneToOne
    private City city;

    @OneToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    List<Role> roles;
}
