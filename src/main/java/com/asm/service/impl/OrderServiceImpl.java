package com.asm.service.impl;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.OrderDetailRepository;
import com.asm.dao.OrderRepository;
import com.asm.dto.MonthlyOrderStatistic;
import com.asm.entity.Account;
import com.asm.entity.Order;
import com.asm.entity.OrderDetail;
import com.asm.service.AccountService;
import com.asm.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	OrderDetailRepository detailRepository;

	@Override
	public Order create(JsonNode orderData) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			// Chuyển đổi dữ liệu JSON thành đối tượng Order
			Order order = mapper.convertValue(orderData, Order.class);

			// Lấy tên người dùng từ SecurityContext
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();

			// Tìm Account tương ứng với tên người dùng
			Account account = accountService.findById(username); // Giả sử findById(username) trả về đối tượng Account

			if (account == null) {
				throw new RuntimeException("User account not found");
			}

			// Thiết lập Account cho Order
			order.setAccount(account);

			// Lưu đối tượng Order vào cơ sở dữ liệu
			orderRepository.save(order);

			// Chuyển đổi và lưu OrderDetail
			TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
			};
			List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type);
			details.forEach(d -> d.setOrder(order));
			detailRepository.saveAll(details);

			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to create order: " + e.getMessage());
		}
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public List<Object[]> getMonthlyOrderStatistics(Integer year) {
		return orderRepository.getMonthlyOrderStatistics(year);
	}

}
