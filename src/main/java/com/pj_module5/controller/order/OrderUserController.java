package com.pj_module5.controller.order;

import com.pj_module5.dto.request.cart.CartRequestDTO;
import com.pj_module5.model.Orders;
import com.pj_module5.model.OrdersDetail;
import com.pj_module5.service.order.OrdersService;
import com.pj_module5.service.order_detail.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderUserController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Orders>> getAllUserOrder(@PathVariable("userId") String userId) {
        try {
            return new ResponseEntity<>(ordersService.findByUser(Long.valueOf(userId)), HttpStatus.OK);
        } catch (Exception ignored) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@Valid @RequestBody CartRequestDTO cartRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            List<String> errorMess = new ArrayList<>();
            for (ObjectError objectError :objectErrors) {
                errorMess.add(objectError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMess, HttpStatus.BAD_REQUEST);
        }
        Orders orders = ordersService.save(cartRequestDTO);
        if (orders == null) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/order-detail/{id}")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable("id") String id) {
        try {
            List<OrdersDetail> ordersDetails = orderDetailService.findByOrders(ordersService.findById(Long.valueOf(id)));
            return new ResponseEntity<>(ordersDetails, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
