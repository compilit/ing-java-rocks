package nl.ing.java.rocks.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nl.ing.java.rocks")
public class JavaRocksApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaRocksApplication.class);
  }
}
