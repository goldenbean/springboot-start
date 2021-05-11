package org.apache.livy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.netty.util.concurrent.Promise;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.apache.livy.rsc.ContextInfo;
import org.apache.livy.rsc.FutureListener;
import org.apache.livy.rsc.RSCConf;
import org.apache.livy.rsc.Utils;
import org.apache.livy.rsc.rpc.RegistrationHandlerV2;
import org.apache.livy.rsc.rpc.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LivyRSCServer {

  private static final Logger logger = LoggerFactory.getLogger(LivyRSCClient.class);

  private static final ObjectMapper mapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);

  private static final Object shutdownLock = new Object();
  private static boolean running = true;

  public static LivyRSCServer create() throws Exception {

    RSCConf conf = new RSCConf();
    RpcServer server = new RpcServer(conf, 30050);

    Promise<ContextInfo> contextInfoPromise = server.getEventLoopGroup().next().newPromise();
    Utils.addListener(contextInfoPromise, new FutureListener<ContextInfo>() {
      @Override
      public void onSuccess(ContextInfo info) throws Exception {

        String url = String.format("rsc://%s:%s@%s:%d",
            info.clientId, info.secret, info.remoteAddress, info.remotePort);

        logger.info("serverUriPromise {}, {}", url, URI.create(url));

      }

      @Override
      public void onFailure(Throwable error) {
        logger.warn("failure", error);
      }
    });

    RegistrationHandlerV2 handler = new RegistrationHandlerV2(contextInfoPromise);
    server.registerClient("DEFAULT", Constants.SECRET, handler);

    return new LivyRSCServer(server);

  }

  public final RpcServer server;

  public LivyRSCServer(RpcServer server) {
    this.server = server;
  }

  public RpcServer getRpcServer() {
    return server;
  }


  public static void main(String[] args) throws Exception {
    LivyRSCServer livyRSCServer = create();

    logger.info("going to sleep");
    TimeUnit.MINUTES.sleep(1);

    synchronized (shutdownLock) {
      try {
        while (running) {
          logger.info("going to wait");
          shutdownLock.wait();
        }
      } catch (InterruptedException ie) {
        // Nothing to do.
      }
    }
  }


}
