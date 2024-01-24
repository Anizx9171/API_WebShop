package com.pj_module5.service.order_detail;

import com.pj_module5.model.Orders;
import com.pj_module5.model.OrdersDetail;
import com.pj_module5.repository.order.OrdersRepository;
import com.pj_module5.repository.order_detail.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrdersDetail> findByOrders(Orders order) {
        return orderDetailRepository.findAllByOrders(order);
    }

    @Override
    public OrdersDetail save(OrdersDetail ordersDetail) {
        return orderDetailRepository.save(ordersDetail);
    }
}
