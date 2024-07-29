package com.asm.service;

import java.util.List;

import com.asm.dto.MonthlyOrderStatistic;
import com.asm.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode orderData);

	 Order findById(Long id) ;

	List<Order> findByUsername(String username);
	
	 List<Object[]> getMonthlyOrderStatistics(Integer year);

}
