package demo.service;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

  @PostConstruct
  public void init() {
    logger.info("init GreetingService ...");
  }
}
