import demo.util.http.HttpRequest;
import demo.util.http.RestResult;
import org.apache.livy.rsc.BaseProtocol.RemoteDriverAddress;
import retrofit2.Response;

public class HttpRequestTests {

  public static void main(String[] args) {

    RemoteDriverAddress remoteDriverAddress = new RemoteDriverAddress(
        "add", 123, "bcd", "def");
    DriverService driverService = HttpRequest.buildRequest("http://hadoop-3:8080/").create(DriverService.class);
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
