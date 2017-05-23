package com.greenfox.peer_to_peer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class DTO {
  Message message;
  Client client;
}
