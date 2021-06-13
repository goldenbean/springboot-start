package demo.spi;

import java.util.ServiceLoader;
import org.junit.Test;

public class SpiTests {


  @Test
  public void test() {
    ServiceLoader<Funny> serviceLoader = ServiceLoader.load(Funny.class);
    serviceLoader.forEach(Funny::deal);
  }


}
