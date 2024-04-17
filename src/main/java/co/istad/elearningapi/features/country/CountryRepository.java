package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository <Country , String> {
    Optional<Country> findByIso(String iso);
}
