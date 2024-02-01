package com.algaworks.example.order.outbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.algaworks.example.order.service.OutboxMessageService;

@Component
public class OutboxScheduler {

	@Autowired
	private OutboxMessageService outboxMessageService;
	
	@Scheduled(fixedRate = 30000L) //30 segundos
	public void sendPendingMessages() {
		outboxMessageService.sendTopPendingMessage();
	}
}
