package demo;

import demo.service.LivyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootMain {
  private static final Logger logger = LoggerFactory.getLogger(LivyService.class);
  
  public static void main(String[] args) {
    SpringApplication.run(BootMain.class, args);
  }

}
