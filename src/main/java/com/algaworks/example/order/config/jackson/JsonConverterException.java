package com.algaworks.example.order.config.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonConverterException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public JsonConverterException(JsonProcessingException e) {
	}
}
