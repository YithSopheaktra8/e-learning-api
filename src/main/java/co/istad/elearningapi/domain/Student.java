package co.istad.elearningapi.domain;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String highSchool;

    private Boolean isBlock;

    private String university;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
