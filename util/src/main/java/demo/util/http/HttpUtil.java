package demo.util.http;

import demo.util.JsonUtil;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

  public static InetAddress findLocalAddress() throws IOException {
    InetAddress address = InetAddress.getLocalHost();
    if (address.isLoopbackAddress()) {
      // Address resolves to something like 127.0.1.1, which happens on Debian;
      // try to find a better address using the local network interfaces
      Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
      while (ifaces.hasMoreElements()) {
        NetworkInterface ni = ifaces.nextElement();
        Enumeration<InetAddress> addrs = ni.getInetAddresses();
        while (addrs.hasMoreElements()) {
          InetAddress addr = addrs.nextElement();
          if (!addr.isLinkLocalAddress() && !addr.isLoopbackAddress()
              && addr instanceof Inet4Address) {
            // We've found an address that looks reasonable!
            logger.warn("Your hostname, {}, resolves to a loopback address; using {} "
                    + " instead (on interface {})", address.getHostName(), addr.getHostAddress(),
                ni.getName());

            return addr;
          }
        }
      }
    }

    logger.warn("Your hostname, {}, resolves to a loopback address, but we couldn't find "
        + "any external IP address!", address.getCanonicalHostName());
    return address;
  }

}
