package com.pj_module5.dto.request.user;

import com.pj_module5.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequestDTO2 {
    @NotNull(message = "ID cannot be null")
    private Long id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean status;
    private Long rolesId;
    private String password;
}
