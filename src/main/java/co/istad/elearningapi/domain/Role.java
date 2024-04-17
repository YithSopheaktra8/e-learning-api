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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_authorities", joinColumns = @JoinColumn(name = "roles_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id",referencedColumnName = "id"))
    List<Authority> authorities;
}
