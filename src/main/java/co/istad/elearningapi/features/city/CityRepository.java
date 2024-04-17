package co.istad.elearningapi.features.city;

import co.istad.elearningapi.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City,String> {
}
