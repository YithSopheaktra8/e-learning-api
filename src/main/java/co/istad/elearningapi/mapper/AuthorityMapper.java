package co.istad.elearningapi.mapper;

import co.istad.elearningapi.domain.Authority;
import co.istad.elearningapi.features.authority.RoleAuthorityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Named("mapAuthorityResponse")
    default List<RoleAuthorityResponse> mapAuthorityResponse(List<Authority> authorities){
        return toAuthorityResponseList(authorities);
    }

    List<RoleAuthorityResponse> toAuthorityResponseList(List<Authority> authority);
}
