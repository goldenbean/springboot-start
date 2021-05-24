package livy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.apache.livy.rsc.BaseProtocol.RemoteDriverAddress;
import org.apache.livy.rsc.ContextInfo;
import org.apache.livy.rsc.RSCClient;
import org.apache.livy.rsc.RSCConf;
import org.apache.livy.rsc.ReplJobResults;
import org.apache.livy.rsc.http.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LivyRSCClient {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClient.class);

  private static Map<String, LivyRSCClient> clientMap = new ConcurrentHashMap<>();

  public static Optional<LivyRSCClient> getLivyRSCClient(RemoteDriverAddress remoteDriverAddress)
      throws Exception {
    LivyRSCClient client = clientMap.get(remoteDriverAddress.getClientId());

    if (client == null) {
      try {

//        ContextInfo info = livyService
//            .getRemoteServer()
//            .getRpcServer()
//            .getContextInfoMap()
//            .get(clientId);

//        if (info == null) {
//          return Optional.empty();
//        }

        logger.info("RemoteDriverAddress {}", remoteDriverAddress);

        LivyRSCClient rscClient = LivyRSCClient
            .create(remoteDriverAddress.getHost(),
                remoteDriverAddress.getPort(),
                remoteDriverAddress.getClientId());

        clientMap.put(remoteDriverAddress.getClientId(), rscClient);
        client = rscClient;

        //todo ping 检查driver是否活着
      } catch (Exception ex) {
        logger.error("", ex);
        return Optional.empty();
      }
    }

    return Optional.of(client);

  }

  public static LivyRSCClient create(String remoteAddress, int remotePort, String clientId)
      throws Exception {
    logger.info("client create [{}] [{}] [{}]", remoteAddress, remotePort, clientId);

    RSCConf conf = new RSCConf();

    ContextInfo info = new ContextInfo(remoteAddress, remotePort, clientId, Constants.SECRET);
    RSCClient client = new RSCClient(conf, info);

    return new LivyRSCClient(client);

  }

  private final RSCClient client;

  public LivyRSCClient(RSCClient client) {
    this.client = client;
  }

  public Future<Integer> submitReplCode(String code) throws Exception {
    return client.submitReplCode(code, "pyspark");

  }

  public Future<ReplJobResults> getReplJobResults() throws Exception {
    return client.getReplJobResults();
  }


}
