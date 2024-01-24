package com.pj_module5.repository.order_detail;

import com.pj_module5.model.Orders;
import com.pj_module5.model.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrdersDetail, Long> {
 List<OrdersDetail> findAllByOrders(Orders orders);
}
