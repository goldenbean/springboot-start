import demo.util.http.RestResult;
import org.apache.livy.rsc.BaseProtocol;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface DriverService {

  @POST("/livy/driver")
  public Call<RestResult> createDriver(@Body BaseProtocol.RemoteDriverAddress remoteDriverAddress);

}
