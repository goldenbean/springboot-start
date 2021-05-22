package livy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.concurrent.Future;
import org.apache.livy.rsc.driver.Statement;
import org.apache.livy.rsc.http.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LivyRSCClientTests {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClientTests.class);

  private static final ObjectMapper mapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);

  public static void main(String[] args) throws Exception {
    String clientId = "c92c201d-bf8e-4d0c-87ec-3503a16e7c0e";
    String remoteAddress = "10.244.25.153";
    int remotePort = 10000;

    LivyRSCClient client = LivyRSCClient.create(remoteAddress, remotePort, clientId);

    Future<Integer> ret1 = client
        .submitReplCode(Constants.CODE_1);
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
