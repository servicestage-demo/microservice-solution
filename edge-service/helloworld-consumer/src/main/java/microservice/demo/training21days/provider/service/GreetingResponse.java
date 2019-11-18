package microservice.demo.training21days.provider.service;

import java.util.Date;

public class GreetingResponse {
  private String msg;

  private Date timestamp;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GreetingResponse{");
    sb.append("msg='").append(msg).append('\'');
    sb.append(", timestamp=").append(timestamp);
    sb.append('}');
    return sb.toString();
  }
}
