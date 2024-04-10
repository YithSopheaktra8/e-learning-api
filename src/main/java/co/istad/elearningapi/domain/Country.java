package co.istad.elearningapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25)
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

    @OneToMany()
    @JoinTable(name = "countries_cities", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "cities_id",referencedColumnName = "id"))
    List<City> cities;
}
