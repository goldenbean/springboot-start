package demo.control;

import demo.util.JsonUtil;
import java.util.Optional;
import livy.LivyRSCClient;
import livy.SubmitRequest;
import org.apache.livy.rsc.ReplJobResults;
import org.apache.livy.rsc.driver.Statement;
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
  public Statement submit(@RequestBody SubmitRequest request) {

    try {
      logger.info("SubmitRequest: {}", JsonUtil.toJson(request));
      Optional<LivyRSCClient> client = LivyRSCClient
          .getLivyRSCClient(request.getRemoteDriverAddress());

      if (client.isPresent()) {
        int id = client.get().submitReplCode(request.getCode()).get();
        ReplJobResults ret = client.get().getReplJobResults(id).get();
        if (ret.statements.length > 1) {
          return ret.statements[0];
        }
      }
    } catch (Exception ex) {

    }
    return null;
  }

  @GetMapping("/statement")
  public ReplJobResults statement(@RequestBody SubmitRequest request) {

    try {
      logger.info("SubmitRequest: {}", JsonUtil.toJson(request));
      Optional<LivyRSCClient> client = LivyRSCClient
          .getLivyRSCClient(request.getRemoteDriverAddress());

      if (client.isPresent()) {
        ReplJobResults ret;

        if (request.getStatementId() > 0) {
          ret = client.get().getReplJobResults(request.getStatementId()).get();
        } else {
          ret = client.get().getReplJobResults().get();
        }

        logger.info("ReplJobResults: {}", JsonUtil.toJson(ret));
        return ret;
      }

    } catch (Exception ex) {

    }
    return null;
  }


  @GetMapping("/cancel")
  public Integer cancel(@RequestBody SubmitRequest request) {

    try {
      logger.info("SubmitRequest: {}", JsonUtil.toJson(request));
      Optional<LivyRSCClient> client = LivyRSCClient
          .getLivyRSCClient(request.getRemoteDriverAddress());

      if (client.isPresent()) {
        if (request.getStatementId() > 0) {
          client.get().cancelReplCode(request.getStatementId());
          return 0;
        }
      }
    } catch (Exception ex) {

    }
    return -1;
  }

}
