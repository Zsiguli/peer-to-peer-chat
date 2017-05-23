package com.greenfox.peer_to_peer.controller;

import com.greenfox.peer_to_peer.model.DTO;
import com.greenfox.peer_to_peer.model.Status;
import com.greenfox.peer_to_peer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageController {

  final static String PEER_ADDRESS = System.getenv("CHAT_APP_PEER_ADDRESS");
  final static String UNIQUE_ID = System.getenv("CHAT_APP_UNIQUE_ID");

  @Autowired
  MessageRepository messageRepository;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public Status receiveNewMessage(@RequestBody DTO dto) {
    if (!dto.getClient().getId().equals(UNIQUE_ID)) {
      messageRepository.save(dto.getMessage());
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(PEER_ADDRESS + "/api/message/receive", dto, Status.class);
    }
    return new Status("ok");
  }
}
