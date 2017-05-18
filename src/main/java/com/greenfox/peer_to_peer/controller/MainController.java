package com.greenfox.peer_to_peer.controller;

import com.greenfox.peer_to_peer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

import static java.util.logging.Level.INFO;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public String homePage() {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request / GET");
    }
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n",
              envName,
              env.get(envName));
    }

    return "index";
  }

  @GetMapping("/enter")
  public String enterPage(Model model) {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request /enter GET");
    }
    model.addAttribute("user", userRepository.findAll());
    return "enter";
  }
}
