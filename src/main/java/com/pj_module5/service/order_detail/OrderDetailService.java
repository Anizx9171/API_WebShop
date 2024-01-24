package com.pj_module5.service.order_detail;

import com.pj_module5.model.Orders;
import com.pj_module5.model.OrdersDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrdersDetail> findByOrders(Orders order);
    OrdersDetail save(OrdersDetail ordersDetail);
}
