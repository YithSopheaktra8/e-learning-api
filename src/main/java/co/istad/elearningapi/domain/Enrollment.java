package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate certifiedAt;

    @Column(nullable = false)
    private String code;

    private LocalDateTime enrolledAt;

    private Boolean isCertified;

    private Boolean isDeleted;

    private Integer progress;

    @OneToOne
    private Course course;

    @OneToMany
    @JoinColumn(name = "student_id")
    List<Student> students;
}
