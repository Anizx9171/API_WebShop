package com.pj_module5.repository.order;

import com.pj_module5.model.Orders;
import com.pj_module5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUser(User user);
}
