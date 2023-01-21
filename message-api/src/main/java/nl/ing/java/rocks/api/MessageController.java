package nl.ing.java.rocks.api;

import nl.ing.java.rocks.core.api.JmsPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final JmsPublisher<String> jmsPublisher;

  public MessageController(JmsPublisher<String> jmsPublisher) {this.jmsPublisher = jmsPublisher;}

  @PostMapping
  void addMessageToQueue(@RequestBody String message) {
    jmsPublisher.publish(message);
  }
}
