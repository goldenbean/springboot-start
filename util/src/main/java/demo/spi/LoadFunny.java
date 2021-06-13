package demo.spi;

import java.util.ServiceLoader;

public class LoadFunny {

  public static void load() {
    ServiceLoader<Funny> serviceLoader = ServiceLoader.load(Funny.class);
    serviceLoader.forEach(Funny::deal);
  }

}
