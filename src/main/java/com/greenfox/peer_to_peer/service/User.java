package com.greenfox.peer_to_peer.service;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

  @Id
  private String name;
}
