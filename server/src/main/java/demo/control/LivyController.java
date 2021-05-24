package demo.control;

import demo.util.JsonUtil;
import java.util.Optional;
import livy.LivyRSCClient;
import livy.SubmitRequest;
import org.apache.livy.rsc.ReplJobResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/livy")
public class LivyController {

  private static final Logger logger = LoggerFactory.getLogger(LivyController.class);

//  @Autowired
//  private LivyService livyService;

  @GetMapping("/")
  public String getContextInfoMap() {
    return "livy server";
  }

//  public Map<String, ContextInfo> getContextInfoMap() {
//    return livyService.getRemoteServer().getRpcServer().getContextInfoMap();
//  }

//  @PostMapping("/driver")
//  public RestResult driver(@RequestBody RemoteDriverAddress remoteDriverAddress) {
//    logger.info("Received: [{}]", JsonUtil.prettyPrint(remoteDriverAddress));
//    String ret = String.format("Received Remote Driver Address: [ %s ].",
//        JsonUtil.prettyPrint(remoteDriverAddress));
//    return RestResult.success(ret);
//  }

  @GetMapping("/submit")
  public Integer submit(@RequestBody SubmitRequest request) throws Exception {
    logger.info("SubmitRequest: {}", JsonUtil.toJson(request));

    Optional<LivyRSCClient> client = LivyRSCClient
        .getLivyRSCClient(request.getRemoteDriverAddress());

    if (client.isPresent()) {
      return client.get().submitReplCode(request.getCode()).get();
    }

    return null;
  }

  @GetMapping("/statement")
  public ReplJobResults statement(@RequestBody SubmitRequest request) throws Exception {
    logger.info("SubmitRequest: {}", JsonUtil.toJson(request));

    Optional<LivyRSCClient> client = LivyRSCClient
        .getLivyRSCClient(request.getRemoteDriverAddress());

    if (client.isPresent()) {
      return client.get().getReplJobResults().get();
    }

    return null;
  }


}
