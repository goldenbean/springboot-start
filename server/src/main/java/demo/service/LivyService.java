package demo.service;

import javax.annotation.PostConstruct;
import org.apache.livy.service.LivyRSCServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LivyService {

  private static final Logger logger = LoggerFactory.getLogger(LivyService.class);

  private LivyRSCServer remoteServer;
  
  @PostConstruct
  public void init() throws Exception {
    logger.info("init livy service");
    remoteServer = LivyRSCServer.create();
  }

  public LivyRSCServer getRemoteServer() {
    return remoteServer;
  }


}
