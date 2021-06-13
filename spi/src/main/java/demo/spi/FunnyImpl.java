package demo.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunnyImpl implements Funny {
  private static final Logger logger = LoggerFactory.getLogger(FunnyImpl.class);

  @Override
  public void deal() {

    System.out.println("FunnyImpl");

    logger.info("FunnyImpl");
  }
}
