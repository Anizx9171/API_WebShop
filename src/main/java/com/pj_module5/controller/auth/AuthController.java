package com.pj_module5.controller.auth;

import com.pj_module5.dto.request.user.UserRequestDTO;
import com.pj_module5.dto.response.user.UserResponseDTO;
import com.pj_module5.dto.response.user.UserResponseDTO2;
import com.pj_module5.model.User;
import com.pj_module5.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?>register(@Valid @RequestBody User user,   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            List<String> errorMess = new ArrayList<>();
            for (ObjectError objectError :objectErrors) {
                errorMess.add(objectError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMess, HttpStatus.BAD_REQUEST);
        }
       try {
           User newUser = userService.register(user);
           return new ResponseEntity<>(new UserResponseDTO2(newUser), HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>("gmail hoặc tên người dùng đã được sử dụng", HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO=userService.login(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

}
