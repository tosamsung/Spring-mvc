package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
