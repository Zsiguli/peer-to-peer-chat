package com.greenfox.peer_to_peer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

import static java.util.logging.Level.INFO;

@Controller
public class MainController {

  @GetMapping("/")
  public String homePage() {
    if (System.getenv("CHAT_APP_LOGLEVEL") == "ERROR") {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request / GET");
    }
    return "index";
  }
}
