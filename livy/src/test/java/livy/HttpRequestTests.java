package livy;

import demo.util.JsonUtil;
import demo.util.http.HttpRequest;
import demo.util.http.RestResult;
import org.apache.livy.rsc.BaseProtocol.RemoteDriverAddress;
import org.apache.livy.rsc.http.DriverService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

public class HttpRequestTests {
  private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

  @Test
  public void testConnection() {

    RemoteDriverAddress remoteDriverAddress = new RemoteDriverAddress(
        "add", 123, "bcd", "def");
    remoteDriverAddress.setCanonicalHostName("CanonicalHostName");
    remoteDriverAddress.setHostName("hostname");

    JsonUtil.prettyPrint(remoteDriverAddress);

    JsonUtil.prettyPrint(RestResult.success("Hello"));

    DriverService driverService = HttpRequest
        .buildRequest("http://localhost:8998/")
        .create(DriverService.class);

    try {
      Response<RestResult> response = driverService.createDriver(remoteDriverAddress).execute();

      System.out.println(response);

      RestResult restResult = response.body();
      System.out.println(restResult);
      System.out.println(restResult.getData().toString());

    } catch (Throwable th) {
      System.err.println(th);
    }
  }

}
