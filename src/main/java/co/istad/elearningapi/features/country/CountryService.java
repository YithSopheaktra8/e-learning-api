package co.istad.elearningapi.features.country;

import co.istad.elearningapi.features.city.dto.CityResponse;
import co.istad.elearningapi.features.country.dto.CountryResponse;

import java.util.List;

public interface CountryService {
    List<CountryResponse> findAllCountries(String name);

    List<CityResponse> findAllCitiesInCountry(String iso);
}
