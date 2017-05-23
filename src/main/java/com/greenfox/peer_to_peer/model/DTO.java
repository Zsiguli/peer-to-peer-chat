package com.greenfox.peer_to_peer.model;

import com.greenfox.peer_to_peer.service.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DTO {
  Message message;
  User client;
}
