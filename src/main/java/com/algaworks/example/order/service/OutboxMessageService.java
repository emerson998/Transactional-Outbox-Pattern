package com.algaworks.example.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.example.order.core.json.JsonConverter;
import com.algaworks.example.order.core.message.MessageSender;
import com.algaworks.example.order.enumerator.Status;
import com.algaworks.example.order.outbox.OutboxMessage;
import com.algaworks.example.order.outbox.OutboxMessageRepository;

@Service
public class OutboxMessageService {

	private static final Logger log = LoggerFactory.getLogger(OutboxMessageService.class);
	
	@Autowired
	private JsonConverter jsonConverter;
	
	@Autowired
	private MessageSender messageSender;

	@Autowired
	private OutboxMessageRepository outboxMessageRepository;

	
	@Transactional
	public OutboxMessage store(String destination, Object object) {
		var json = jsonConverter.toJson(object);
		var outboxMessage = new OutboxMessage(destination, json);
		
		return outboxMessageRepository.save(outboxMessage);
		
	}
	
	@Transactional
	public void sendTopPendingMessage() {
		
		log.info("Enviando mensagens pendentes");
		
		List<OutboxMessage> outboxMessages = 
				outboxMessageRepository.findFirst10ByStatusOrderByCreateAtAsc(Status.PENDING);
		
		for (OutboxMessage outboxMessage : outboxMessages) {
			outboxMessage.increaseTentatives();
			
			try {
				messageSender.send(outboxMessage.getDestination(), outboxMessage.getContent());
			} catch (Exception e) {
				log.error("Erro ao enviar mensagem para o message broker", e);
				
				if(outboxMessage.getTentatives() >= 20) {
					outboxMessage.setStatus(Status.ERROR);
				}
				
				continue;
			}
			
			outboxMessageRepository.delete(outboxMessage);
		}
		
	}
	
}
