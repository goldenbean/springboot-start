package livy;

import org.apache.livy.rsc.BaseProtocol.RemoteDriverAddress;

public class SubmitRequest {

  private RemoteDriverAddress remoteDriverAddress;
  private String code;

  public SubmitRequest() {

  }

  public RemoteDriverAddress getRemoteDriverAddress() {
    return remoteDriverAddress;
  }

  public void setRemoteDriverAddress(
      RemoteDriverAddress remoteDriverAddress) {
    this.remoteDriverAddress = remoteDriverAddress;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
