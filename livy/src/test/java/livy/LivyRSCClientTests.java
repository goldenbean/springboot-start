package livy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.concurrent.Future;
import org.apache.livy.rsc.driver.Statement;
import org.apache.livy.rsc.http.Constants;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LivyRSCClientTests {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClientTests.class);

  private static final ObjectMapper mapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);

  final String clientId = "0cb9f474-b62a-48a8-92a1-cca6680910f9";
  final String remoteAddress = "10.58.10.198";
  final  int remotePort = 10000;
  LivyRSCClient client;

  @Before
  public void setup() throws Exception {
    client = LivyRSCClient.create(remoteAddress, remotePort, clientId);
  }

  @Test
  public void testSubmitReplCode() throws Exception {

    Future<Integer> ret1 = client
        .submitReplCode(Constants.CODE_1);
    logger.info("Future<Integer>  {}", ret1.get());

    Future<Integer> ret2 = client
        .submitReplCode(Constants.CODE_2);

    logger.info("Future<Integer>  {}", ret2.get());

  }


  @Test
  public void testGetReplJobResults() throws Exception {
    Statement[] statements = client.getReplJobResults().get().statements;

    logger.info("total statements {}", statements.length);
    for (Statement statement : statements) {
      logger.info("statements: {}", mapper.writeValueAsString(statement));
    }
  }


}
