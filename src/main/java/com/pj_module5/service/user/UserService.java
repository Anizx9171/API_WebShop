package com.pj_module5.service.user;

import com.pj_module5.dto.request.user.UserRequestDTO;
import com.pj_module5.dto.response.user.UserResponseDTO;
import com.pj_module5.dto.response.user.UserResponseDTO2;
import com.pj_module5.model.Role;
import com.pj_module5.model.User;

import java.util.List;

public interface UserService {
    User register(User user);
    UserResponseDTO login(UserRequestDTO userRequestDTO);
    List<UserResponseDTO2> findAll();
    List<UserResponseDTO2> findAllByName(String name);
    UserResponseDTO2 findById(Long id);
    User findUserById(Long id);
    UserResponseDTO2 save(User user);
    void deleteById(Long id);
}
