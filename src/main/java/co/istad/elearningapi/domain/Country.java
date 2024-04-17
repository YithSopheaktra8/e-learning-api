package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 300)
    private String flag;

    @Column(length = 10, nullable = false)
    private String iso;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 25)
    private String niceName;

    @Column(nullable = false)
    private Integer numCode;

    @Column(nullable = false)
    private Integer phoneCode;

    @OneToMany(mappedBy = "country")
    List<City> cities;
}
