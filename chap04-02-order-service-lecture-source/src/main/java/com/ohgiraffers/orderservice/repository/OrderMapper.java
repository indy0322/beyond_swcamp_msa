package com.ohgiraffers.orderservice.repository;

import com.ohgiraffers.orderservice.aggregate.Order;
import com.ohgiraffers.orderservice.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> selectOrderByUserId(int userId);
}
