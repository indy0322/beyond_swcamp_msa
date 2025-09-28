package com.ohgiraffers.orderservice.service;

import com.ohgiraffers.orderservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    List<OrderDTO> getOrderByUserId(int userId);
}
