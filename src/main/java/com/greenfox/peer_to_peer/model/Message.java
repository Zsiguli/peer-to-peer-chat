package com.greenfox.peer_to_peer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class Message {

  String username;
  String text;
  Timestamp timestamp;
  @Id
  Long id;

  public Message(String user, String message) {
    username = user;
    this.text = message;
    timestamp = new Timestamp(System.currentTimeMillis());
    id = (long) (Math.pow(10, 6) + Math.random() * Math.pow(10, 7));
  }
}
