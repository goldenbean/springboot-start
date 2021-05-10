package org.apache.livy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.concurrent.Future;
import org.apache.livy.rsc.ContextInfo;
import org.apache.livy.rsc.RSCClient;
import org.apache.livy.rsc.RSCConf;
import org.apache.livy.rsc.ReplJobResults;
import org.apache.livy.rsc.driver.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LivyRSCClient {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClient.class);

  private static final ObjectMapper mapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);


  public static LivyRSCClient create(String remoteAddress, int remotePort, String clientId) throws Exception {
    logger.info("client start");

    RSCConf conf = new RSCConf();

    ContextInfo info = new ContextInfo(remoteAddress, remotePort, clientId, org.apache.livy.service.Constants.SECRET);
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

  public static void main(String[] args) throws Exception {
    String clientId = "a45060f4-b17e-4918-a840-0e3db6d04e05";
    String remoteAddress = "10.58.11.90";
    int remotePort = 10000;

    LivyRSCClient client = LivyRSCClient.create(remoteAddress, remotePort, clientId);

    Future<Integer> ret1 = client
        .submitReplCode(org.apache.livy.service.Constants.CODE_1);
    logger.info("Future<Integer>  {}", ret1.get());

    Future<Integer> ret2 = client
        .submitReplCode(Constants.CODE_2);

    logger.info("Future<Integer>  {}", ret2.get());

    Statement[] statements = client.getReplJobResults().get().statements;

    logger.info("total statements {}", statements.length);
    for (Statement statement : statements) {
      logger.info("statements: {}", mapper.writeValueAsString(statement));
    }
  }

}
