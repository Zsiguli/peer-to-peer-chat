package com.greenfox.peer_to_peer.controller;

import com.greenfox.peer_to_peer.repository.UserRepository;
import com.greenfox.peer_to_peer.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

import static java.util.logging.Level.INFO;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public String homePage(Model model) {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request / GET");
    }
    if (userRepository.count() == 0) {
      return "redirect:/enter";
    } else {
      model.addAttribute("users", userRepository.findAll());
      return "index";
    }
  }

  @GetMapping("/enter")
  public String enterPage() {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request /enter GET");
    }
    if (userRepository.count() != 0) {
      return "redirect:/";
    } else {
      return "enter";
    }
  }

  @PostMapping("/addUser")
  public String addUser(@RequestParam("name") String name) {
    if (userRepository.count() == 0) {
      userRepository.save(new User(name));
    }
    return "redirect:/";
  }

  @PostMapping("/upgradeUser")
  public String upgradeUser(@RequestParam("name") String name) {
    User user = userRepository.findOne((long) 1);
    System.out.println(user.getName());
    user.setName(name);
    userRepository.save(user);
    return "redirect:/";
  }
}
