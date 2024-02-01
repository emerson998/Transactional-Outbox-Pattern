package com.algaworks.example.order.core.message;

public interface MessageSender {

	void send(String destination, String content);
}
