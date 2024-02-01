package com.algaworks.example.order.outbox;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.algaworks.example.order.enumerator.Status;


@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long>{

	//Enquanto não finalizar as linhas estão travadas para select
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<OutboxMessage> findFirst10ByStatusOrderByCreateAtAsc(Status status);
}
