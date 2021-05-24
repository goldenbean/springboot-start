package demo.util;

import java.util.Timer;
import java.util.TimerTask;
import org.junit.Test;

public class TimeUtilTests {


  @Test
  public void test() {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println(TimeUtil.getTimeStamp());
      }
    }, 0, 2 * 1000);

    TimeUtil.sleep(60);
  }
}
