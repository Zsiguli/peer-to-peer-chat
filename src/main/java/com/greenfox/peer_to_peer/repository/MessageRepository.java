package com.greenfox.peer_to_peer.repository;

import com.greenfox.peer_to_peer.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
  Iterable<Message> findAllByOrderByTimestampDesc();
}
