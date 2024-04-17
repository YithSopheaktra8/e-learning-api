package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Country;
import co.istad.elearningapi.features.country.dto.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "name", target = "countryName")
    List<CountryResponse> toDTO(List<Country> countries);
}
