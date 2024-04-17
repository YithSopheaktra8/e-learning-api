package co.istad.elearningapi.features.country;

import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.city.CityRepository;
import co.istad.elearningapi.features.city.dto.CityResponse;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import co.istad.elearningapi.mapper.CityMapper;
import co.istad.elearningapi.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CountryResponse> findAllCountries(String name) {
        Sort sort = Sort.by(Sort.Direction.ASC,"name");
        List<Country> countries = countryRepository.findAll(sort);
        if(countries.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "There is no countries!"
            );
        }
        return countryMapper.toListCountryResponse(countries);
    }

    @Override
    public List<CityResponse> findAllCitiesInCountry(String iso) {
        Optional<Country> countryOptional = countryRepository.findByIso(iso);
        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            return country.getCities().stream()
                    .map(cityMapper::toCityResponse)
                    .collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with ISO code: " + iso);
        }
    }


}
