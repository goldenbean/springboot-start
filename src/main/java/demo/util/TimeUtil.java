package demo.util;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

  private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

  public static void sleep(long timeout) {
    try {
      TimeUnit.SECONDS.sleep(timeout);
    } catch (InterruptedException ex) {
      logger.warn("", ex);
    }
  }

}
