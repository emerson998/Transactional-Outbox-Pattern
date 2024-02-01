package com.algaworks.example.order.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.example.order.domain.Order;
import com.algaworks.example.order.domain.OrderRepository;
import com.algaworks.example.order.event.OrderCreatedEvent;

@Service
public class OrderRegistrationService {

	@Autowired
	private OutboxMessageService outboxMessageService;

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public Order register(Order order) {
		orderRepository.save(order);
		var event = new OrderCreatedEvent(order.getId(), order.getValue());
		var destination = "orders.v1.order.created";
		outboxMessageService.store(destination, event);
		
		return order;
	}
}
