package com.greenfox.peer_to_peer.repository;

import com.greenfox.peer_to_peer.service.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
