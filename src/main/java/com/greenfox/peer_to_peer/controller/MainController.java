package com.greenfox.peer_to_peer.controller;

import com.greenfox.peer_to_peer.model.Client;
import com.greenfox.peer_to_peer.model.DTO;
import com.greenfox.peer_to_peer.model.Message;
import com.greenfox.peer_to_peer.model.Status;
import com.greenfox.peer_to_peer.repository.MessageRepository;
import com.greenfox.peer_to_peer.repository.UserRepository;
import com.greenfox.peer_to_peer.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static com.greenfox.peer_to_peer.controller.MessageController.PEER_ADDRESS;
import static com.greenfox.peer_to_peer.controller.MessageController.UNIQUE_ID;
import static java.util.logging.Level.INFO;

@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  MessageRepository messageRepository;

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
      model.addAttribute("messages", messageRepository.findAllByOrderByTimestampDesc());
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
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request /addUser POST");
    }
    if (userRepository.count() == 0) {
      userRepository.save(new User(name));
    }
    return "redirect:/";
  }

  @PostMapping("/upgradeUser")
  public String upgradeUser(@RequestParam("name") String name) {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.out.println("only errors");
    } else {
      System.out.println(new Date() + " " +
              INFO.getName() +
              " Request /upgradeUser POST");
    }
    User user = userRepository.findOne((long) 1);
    user.setUsername(name);
    userRepository.save(user);
    return "redirect:/";
  }

  @PostMapping("/sendMessage")
  public String sendMessage(@RequestParam String text) {
    DTO dto = new DTO();
    dto.setMessage(new Message(userRepository.findOne((long) 1).getUsername(), text));
    dto.setClient(new Client(UNIQUE_ID));
    messageRepository.save(dto.getMessage());
    System.out.println("enviroment variable \n\n" + PEER_ADDRESS + "\n");
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(PEER_ADDRESS + "/api/message/receive", dto, Status.class);
    return "redirect:/";
  }
}
