package com.pj_module5.repository.order_status;

import com.pj_module5.model.StatusOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<StatusOrders, Long> {
    StatusOrders findByStatusName(String name);
}
