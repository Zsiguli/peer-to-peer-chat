package com.greenfox.peer_to_peer.model;

public class Error extends Status {

  String message;

  public Error(String status) {
    super(status);
    message = "Missing field(s): message.timestamp, client.id";
  }
}
