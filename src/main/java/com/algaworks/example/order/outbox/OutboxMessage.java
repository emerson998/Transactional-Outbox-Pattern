package com.algaworks.example.order.outbox;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.algaworks.example.order.enumerator.Status;

@Entity
@Table(name = "tb_outbox_message")
public class OutboxMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private OffsetDateTime createAt = OffsetDateTime.now();

	private String destination;

	@Column(columnDefinition = "text")
	private String content;

	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	private int tentatives;

	public OutboxMessage() {
	}

	public OutboxMessage(String destination, String content) {
		this.destination = destination;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(OffsetDateTime createAt) {
		this.createAt = createAt;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getTentatives() {
		return tentatives;
	}

	public void setTentatives(int tentatives) {
		this.tentatives = tentatives;
	}

}
