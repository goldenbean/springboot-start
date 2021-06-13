package demo.service;

import demo.spi.LoadFunny;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FunnyService {

  private static final Logger logger = LoggerFactory.getLogger(FunnyService.class);

  @PostConstruct
  public void init() {

    logger.info("init FunnyService");

    LoadFunny.load();
  }

}
