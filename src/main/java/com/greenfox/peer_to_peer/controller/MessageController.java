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
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("all fields: " + dto.getClass().getDeclaredFields());
    System.out.println("client fields: " + dto.getClient().getClass().getDeclaredFields());
    System.out.println("client id: " + dto.getClient().getId());
    System.out.println("message fields: " + dto.getMessage().getClass().getDeclaredFields());
    System.out.println("msg id: " + dto.getMessage().getId());
    System.out.println("msg text: " + dto.getMessage().getText());
    System.out.println("msg timestamp: " + dto.getMessage().getTimestamp());
    System.out.println("msg username: " + dto.getMessage().getUsername());
    System.out.println();
    System.out.println();
    System.out.println();
    if (!dto.getClient().getId().equals(UNIQUE_ID)) {
      messageRepository.save(dto.getMessage());
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(PEER_ADDRESS + "/api/message/receive", dto, Status.class);
    }
    Status status = new Status("ok");
    System.out.println();
    System.out.println();
    System.out.println("status: " + status);
    System.out.println();
    System.out.println();
    return status;
  }
}
