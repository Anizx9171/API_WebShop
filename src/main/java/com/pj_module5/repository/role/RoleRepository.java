package com.pj_module5.repository.role;

import com.pj_module5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String roleName);
}
