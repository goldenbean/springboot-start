package livy;

import java.util.concurrent.Future;
import org.apache.livy.rsc.ContextInfo;
import org.apache.livy.rsc.RSCClient;
import org.apache.livy.rsc.RSCConf;
import org.apache.livy.rsc.ReplJobResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LivyRSCClient {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClient.class);


  public static LivyRSCClient create(String remoteAddress, int remotePort, String clientId)
      throws Exception {
    logger.info("client start");

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
