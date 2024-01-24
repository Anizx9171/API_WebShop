package com.pj_module5.service.user;

import com.pj_module5.dto.request.user.UserRequestDTO;
import com.pj_module5.dto.response.user.UserResponseDTO;
import com.pj_module5.dto.response.user.UserResponseDTO2;
import com.pj_module5.model.Role;
import com.pj_module5.model.User;
import com.pj_module5.repository.user.UserReponsitory;
import com.pj_module5.security.jwt.JWTProvider;
import com.pj_module5.security.principle.UserPrinciple;
import com.pj_module5.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public User register(User user) {
        //ma hoa mat khau
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //role
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName("CUSTOMER"));
        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setStatus(user.getStatus());
        newUser.setAddress(user.getAddress());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setRoles(roles);
        return userReponsitory.save(newUser);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUserName(), userRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        System.out.println(userPrinciple.getUsername());
        return UserResponseDTO.builder().
                token(jwtProvider.generateToken(userPrinciple)).userName(userPrinciple.getUsername())
                .roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).build();
    }

    @Override
    public List<UserResponseDTO2> findAll() {
        List<User> users = userReponsitory.findAll();
        List<UserResponseDTO2> responseDTOs = new ArrayList<>();
        users.forEach(user -> {
            responseDTOs.add(new UserResponseDTO2(user));
        });
        return responseDTOs;
    }

    @Override
    public List<UserResponseDTO2> findAllByName(String name) {
        List<User> users = userReponsitory.findAllByUserNameContainsIgnoreCase(name);
        List<UserResponseDTO2> responseDTOs = new ArrayList<>();
        users.forEach(user -> {
            responseDTOs.add(new UserResponseDTO2(user));
        });
        return responseDTOs;
    }

    @Override
    public UserResponseDTO2 findById(Long id){
            User user = userReponsitory.findById(id).orElse(null);
            if (user != null) {
                return new UserResponseDTO2(user);
            }
            return null;
    }

    @Override
    public UserResponseDTO2 save(User user) {
        User user1 = userReponsitory.save(user);
        return new UserResponseDTO2(user1);
    }

    @Override
    public void deleteById(Long id) {
        userReponsitory.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userReponsitory.findById(id).orElse(null);
    }
}
