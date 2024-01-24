package com.pj_module5.service.role;

import com.pj_module5.model.Role;
import com.pj_module5.model.Role;

import java.util.List;

public interface RoleService {
    Role findByRoleName(String roleName);
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
}
