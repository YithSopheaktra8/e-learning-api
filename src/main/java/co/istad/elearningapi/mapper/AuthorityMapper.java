package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.features.authority.AuthorityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Named("mapAuthorityResponse")
    default List<AuthorityResponse> mapAuthorityResponse(List<Authority> authorities){
        return toAuthorityResponseList(authorities);
    }

    List<AuthorityResponse> toAuthorityResponseList(List<Authority> authority);
}
