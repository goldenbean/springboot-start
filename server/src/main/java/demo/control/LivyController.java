package demo.control;

import demo.bean.SubmitRequest;
import demo.service.LivyService;
import demo.util.JsonUtil;
import demo.util.http.RestResult;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.livy.rsc.BaseProtocol.RemoteDriverAddress;
import org.apache.livy.rsc.ContextInfo;
import org.apache.livy.rsc.ReplJobResults;
import livy.LivyRSCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/livy")
public class LivyController {

  private static final Logger logger = LoggerFactory.getLogger(LivyController.class);

  private Map<String, LivyRSCClient> clients = new ConcurrentHashMap<>();

  @Autowired
  private LivyService livyService;

  @GetMapping("/")
  public Map<String, ContextInfo> getContextInfoMap() {
    return livyService.getRemoteServer().getRpcServer().getContextInfoMap();
  }

  @PostMapping("/driver")
  public RestResult driver(@RequestBody RemoteDriverAddress remoteDriverAddress) {
    logger.info("Received: [{}]", JsonUtil.prettyPrint(remoteDriverAddress));
    String ret = String.format("Received Remote Driver Address: [ %s ].",
        JsonUtil.prettyPrint(remoteDriverAddress));
    return RestResult.success(ret);
  }

  @GetMapping("/submit")
  public Integer submit(@RequestBody SubmitRequest request) throws Exception {

    Optional<LivyRSCClient> client = getLivyRSCClient(request.clientId);

    if (client.isPresent()) {
      return client.get().submitReplCode(request.code).get();
    }

    return null;
  }

  @GetMapping("/statements")
  public ReplJobResults statement(@RequestBody SubmitRequest request) throws Exception {

    Optional<LivyRSCClient> client = getLivyRSCClient(request.clientId);

    if (client.isPresent()) {
      return client.get().getReplJobResults().get();
    }

    return null;
  }

  private Optional<LivyRSCClient> getLivyRSCClient(String clientId) throws Exception {
    LivyRSCClient client = clients.get(clientId);

    if (client == null) {
      try {
        ContextInfo info = livyService.getRemoteServer().getRpcServer().getContextInfoMap()
            .get(clientId);

        if (info == null) {
          return Optional.empty();
        }

        LivyRSCClient rscClient = LivyRSCClient
            .create(info.remoteAddress, info.remotePort, info.clientId);

        clients.put(clientId, rscClient);
        client = rscClient;

      } catch (Exception ex) {
        logger.error("", ex);
        return Optional.empty();
      }
    }

    return Optional.of(client);

  }
}
