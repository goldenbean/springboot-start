package demo.util.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import demo.util.JsonUtil;

public class RestResult<T> {

  public static <T> RestResult success(T data) {
    return new RestResult(true, 200, "success", data);
  }

  public static <T> RestResult failed(T data) {
    return new RestResult(false, 500, "failed", "");
  }

  @JsonProperty(value = "success")
  private boolean success = true;

  @JsonProperty(value = "code")
  private int code = 200;

  @JsonProperty(value = "message")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message = "";

  @JsonProperty(value = "data")
  private T data;

  public RestResult() {
  }

  public RestResult(boolean success, int code, String message, T data) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  @Override
  public String toString() {
    return JsonUtil.prettyPrint(this);
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
