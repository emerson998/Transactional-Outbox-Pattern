package com.algaworks.example.order.config.jackson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.example.order.core.json.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverterJackImpl implements JsonConverter{


	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new JsonConverterException(e);
		}
	}


}
