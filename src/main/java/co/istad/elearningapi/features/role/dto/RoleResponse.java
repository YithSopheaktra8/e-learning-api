package co.istad.elearningapi.features.role.dto;

import co.istad.elearningapi.features.authority.RoleAuthorityResponse;

import java.util.List;

public record RoleResponse(
        String name,
        List<RoleAuthorityResponse> authorities
) {
}
