package com.pj_module5.controller.user;

import com.pj_module5.dto.request.user.UserRequestDTO2;
import com.pj_module5.dto.response.user.UserResponseDTO2;
import com.pj_module5.model.User;
import com.pj_module5.service.role.RoleService;
import com.pj_module5.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO2 userRequestDTO2){
        try {
            User userFind = userService.findUserById(id);
            if (userFind != null){
                    userFind.setAddress(userRequestDTO2.getAddress());
                    userFind.setPhoneNumber(userRequestDTO2.getPhoneNumber());
                    UserResponseDTO2 userEdit = userService.save(userFind);
                    return new ResponseEntity<>(userEdit, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePass(@PathVariable("id") Long id, @RequestBody UserRequestDTO2 userRequestDTO2){
        User userFind = userService.findUserById(id);
        if (userFind != null){
          userFind.setPassword(passwordEncoder.encode(userRequestDTO2.getPassword()));
            UserResponseDTO2 userEdit = userService.save(userFind);
            return new ResponseEntity<>(userEdit, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") String id){
        try{
            UserResponseDTO2 response = userService.findById(Long.valueOf(id));
            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
