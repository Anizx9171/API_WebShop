package com.pj_module5.dto.response.user;

import com.pj_module5.model.Role;
import com.pj_module5.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO2 {
    private Long id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean status;
    private Set<Role> roles;

    public UserResponseDTO2(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.status = user.getStatus();
        this.roles = user.getRoles();
    }
}
