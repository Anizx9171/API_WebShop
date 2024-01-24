package com.pj_module5.service.order;

import com.pj_module5.dto.request.cart.CartRequestDTO;
import com.pj_module5.model.Orders;
import com.pj_module5.model.User;

import java.util.List;

public interface OrdersService {
   List<Orders> findAll();
   List<Orders> findByUser(Long user);
   Orders findById(Long id);
   Orders appectOrders(Long id);
   Orders denyOrders(Long id);

   Orders save(CartRequestDTO cartRequestDTO);

}
