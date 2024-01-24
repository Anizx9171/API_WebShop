package com.pj_module5.controller.user;

import com.pj_module5.dto.request.user.UserRequestDTO2;
import com.pj_module5.dto.response.user.UserResponseDTO;
import com.pj_module5.dto.response.user.UserResponseDTO2;
import com.pj_module5.model.Role;
import com.pj_module5.model.User;
import com.pj_module5.service.role.RoleService;
import com.pj_module5.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(name = "search", defaultValue = "") String search){
        if (search.isEmpty()){
            List<UserResponseDTO2> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        List<UserResponseDTO2> users = userService.findAllByName(search);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editRoleAndStatus(@PathVariable("id") Long id, @RequestBody UserRequestDTO2 userRequestDTO2){
        User userFind = userService.findUserById(id);
        if (userFind != null){
            Role role = roleService.findById(userRequestDTO2.getRolesId());
            Set<Role> roles = new HashSet<>();
            if (role != null){
                roles.add(role);
                userFind.setRoles(roles);
                userFind.setStatus(userRequestDTO2.getStatus());
                UserResponseDTO2 userEdit = userService.save(userFind);
                return new ResponseEntity<>(userEdit, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
