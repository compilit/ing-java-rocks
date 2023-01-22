package nl.ing.java.rocks.api;

import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import nl.ing.java.rocks.core.Resources;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;
import org.xml.sax.SAXException;

@EnableRabbit
@Configuration
class MessageConsumerConfiguration {

  private final MessageConsumerProperties messageConsumerProperties;

  public MessageConsumerConfiguration(MessageConsumerProperties messageConsumerProperties) {this.messageConsumerProperties = messageConsumerProperties;}

  @Bean
  ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory();
  }

  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    var rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory);
    return rabbitTemplate;
  }

  @Bean
  ErrorHandler errorHandler() {
    return throwable -> {
      //todo: send to dead-letter adter x retries
      var logger = LoggerFactory.getLogger(MessageConsumer.class);
      logger.error("Error while processing message: {}", throwable.getMessage());
    };
  }

  @Bean
  Validator initXsdValidator() throws SAXException, IOException {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Source schemaFile = Resources.getResourceAsSource(messageConsumerProperties.getNoteXsdPath());
    Schema schema = factory.newSchema(schemaFile);
    return schema.newValidator();
  }


}