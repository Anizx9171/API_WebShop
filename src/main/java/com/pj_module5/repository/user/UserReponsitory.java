package com.pj_module5.repository.user;

import com.pj_module5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReponsitory extends JpaRepository<User,Long> {
    User findByUserName(String username);

    List<User> findAllByUserNameContainsIgnoreCase(String username);
}
