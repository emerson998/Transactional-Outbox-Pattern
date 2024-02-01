package com.algaworks.example.order.config.rabbitmq;

import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.example.order.core.message.MessageSender;

@Component
public class MessageSenderRabbitMQImpl implements MessageSender{

	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void send(String destination, String content) {
		var message = MessageBuilder.withBody(content.getBytes()).build();
		rabbitTemplate.send(destination, "", message);
	}

	
}
