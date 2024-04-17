package co.istad.elearningapi.features.city;

import co.istad.elearningapi.features.city.dto.CityResponse;

import java.util.List;

public interface CityService {
    List<CityResponse> findAll(String name);

    List<CityResponse> findAllCitiesInCountry(String iso);
}
