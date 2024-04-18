package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.City;
import co.istad.elearningapi.features.city.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(source = "country.name", target = "country")
    CityResponse toCityResponse(City city);

    List<CityResponse> toListCityResponse(List<City> cities);
}
