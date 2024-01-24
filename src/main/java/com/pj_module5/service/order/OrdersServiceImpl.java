package com.pj_module5.service.order;

import com.pj_module5.dto.request.cart.CartRequestDTO;
import com.pj_module5.dto.request.cart.CurProductRequest;
import com.pj_module5.model.Orders;
import com.pj_module5.model.OrdersDetail;
import com.pj_module5.model.Product;
import com.pj_module5.model.User;
import com.pj_module5.repository.order.OrdersRepository;
import com.pj_module5.repository.order_status.OrderStatusRepository;
import com.pj_module5.service.order_detail.OrderDetailService;
import com.pj_module5.service.product.ProductService;
import com.pj_module5.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProductService productService;
    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> findByUser(Long user) {
        return ordersRepository.findAllByUser(userService.findUserById(user));
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    @Override
    public Orders appectOrders(Long id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders != null){
            if (orders.getStatusOrders().getStatusName().equals("WAITING")){
                orders.setStatusOrders(orderStatusRepository.findByStatusName("CONFIRMED"));
                return ordersRepository.save(orders);
            }
        }
        return null;
    }

    @Override
    public Orders denyOrders(Long id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders != null){
            if (orders.getStatusOrders().getStatusName().equals("WAITING")){
                orders.setStatusOrders(orderStatusRepository.findByStatusName("DENIED"));
                return ordersRepository.save(orders);
            }
        }
        return null;
    }
    @Transactional(rollbackOn = Exception.class)
    @Override
    public Orders save(CartRequestDTO cartRequestDTO) {
       try {
           if (userService.findUserById(cartRequestDTO.getUserId()) == null){
               throw new Exception("User not found");
           }
           long millis = System.currentTimeMillis();
           Orders orders = new Orders();
           orders.setStatusOrders(orderStatusRepository.findByStatusName("WAITING"));
           orders.setUser(userService.findUserById(cartRequestDTO.getUserId()));
           orders.setDate(new Date(millis));
           orders.setTotalPrice(cartRequestDTO.getTotalPrice());
           Orders od = ordersRepository.save(orders);
           for ( CurProductRequest product : cartRequestDTO.getProducts()) {
               OrdersDetail ordersDetail = new OrdersDetail();
               Product product1 = productService.findById(product.getId());
               if (product1 == null){
                   throw new Exception("product not found");
               }
               ordersDetail.setOrders(orders);
               ordersDetail.setPrice(product.getPrice());
               ordersDetail.setProduct(product1);
               ordersDetail.setQuantity(product.getStock());
               orderDetailService.save(ordersDetail);
           }
           return od;
       }catch (Exception ignored){
           System.out.println(ignored.getMessage());
           return null;
       }
    }
}
