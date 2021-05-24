package livy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LivyService {

  private static final Logger logger = LoggerFactory.getLogger(LivyService.class);

  private LivyRSCServer remoteServer;


  public void init() throws Exception {
    logger.info("init livy service");
    remoteServer = LivyRSCServer.create();
  }

  public LivyRSCServer getRemoteServer() {
    return remoteServer;
  }


}
