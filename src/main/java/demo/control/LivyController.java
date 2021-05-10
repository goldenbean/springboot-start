package demo.control;

import demo.service.LivyService;
import java.util.Map;
import org.apache.livy.rsc.ContextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LivyController {

  @Autowired
  private LivyService livyService;

  @GetMapping("/livy")
  public Map<String, ContextInfo> getContextInfoMap() {
    return livyService.getRemoteServer().getRpcServer().getContextInfoMap();
  }
}
