package demo.util.http;

import java.io.IOException;
import java.net.InetAddress;
import org.junit.Test;

public class HttpUtilTests {

  @Test
  public void testFindLocalAddress() throws IOException {
    InetAddress address = HttpUtil.findLocalAddress();

    StringBuilder buff = new StringBuilder();
    buff.append(address.getHostAddress()).append(";")
        .append(address.getHostName()).append(";")
        .append(address.getCanonicalHostName());

    System.out.println(buff.toString());
  }

}
