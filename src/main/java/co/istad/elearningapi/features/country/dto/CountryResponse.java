package co.istad.elearningapi.features.country.dto;

public record CountryResponse(
        Integer id,
        String flag,
        String iso,
        String name,
        String niceName,
        Integer numCode,
        Integer phoneCode
) {
}
