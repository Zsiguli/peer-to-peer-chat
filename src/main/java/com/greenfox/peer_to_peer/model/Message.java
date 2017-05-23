package com.greenfox.peer_to_peer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

  String username;
  String message;
  Timestamp timestamp;
  @Id
  Long id;

  public Message(String user, String message) {
    username = user;
    this.message = message;
    timestamp = new Timestamp(System.currentTimeMillis());
    id = (long) (Math.pow(10, 6) + Math.random() * Math.pow(10, 7));
  }
}
