package com.pj_module5.controller.order;

import com.pj_module5.model.Orders;
import com.pj_module5.service.order.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderAdminController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("")
    public ResponseEntity<List<Orders>> getAllOrder() {
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable("id") String id) {
        try {
            Orders orders = ordersService.findById(Long.valueOf(id));
            if (orders != null) {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<?> orderConfirm(@PathVariable("id") String id) {
       try {
           Orders orders = ordersService.appectOrders(Long.valueOf(id));
           if (orders != null){
               return new ResponseEntity<>(orders, HttpStatus.OK);
           }
       }catch (Exception e){
       }
       return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/denied/{id}")
    public ResponseEntity<?> orderDenied(@PathVariable("id") String id) {
        try {
            Orders orders = ordersService.denyOrders(Long.valueOf(id));
            if (orders != null){
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        }catch (Exception e){
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}