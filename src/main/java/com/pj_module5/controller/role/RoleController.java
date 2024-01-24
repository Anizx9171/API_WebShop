package com.pj_module5.controller.role;

import com.pj_module5.model.Role;
import com.pj_module5.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRole() {
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRoleById(@PathVariable("id") Long id) {
        Role role = roleService.findById(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(role, HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role){
        try {
               Role newRole = roleService.save(role);
                return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Du lieu khong dung", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable("id") Long id, Role role){
        if (roleService.findById(id) != null){
           try{
               role.setId(id);
               Role roleUpdate = roleService.save(role);
               return new ResponseEntity<>(roleUpdate, HttpStatus.OK);
           }catch (Exception e){
               new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
           }
        }
       return new ResponseEntity<>("Khong ton tai", HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id){
       try {
           roleService.deleteById(id);
          return new ResponseEntity<>("Delete success", HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("Delete error", HttpStatus.BAD_REQUEST);
       }
    }
}
