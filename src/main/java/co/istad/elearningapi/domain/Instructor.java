package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String biography;

    private String familyName;

    private String github;

    private String givenName;

    private Boolean isBlocked;

    private Boolean isDeleted;

    private String jobTitle;

    private String linkedIn;

    @Column(nullable = false)
    private String nationalIdCard;

    private String profile;

    private String website;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
