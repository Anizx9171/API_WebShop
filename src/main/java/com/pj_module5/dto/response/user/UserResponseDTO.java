package com.pj_module5.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private String token;
    private String userName;
    private Set<String> roles;
}
