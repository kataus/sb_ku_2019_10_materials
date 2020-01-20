package ru.itvitality.sbrf.cu;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itvitality.sbrf.cu.messagesystem.*;
import ru.itvitality.sbrf.cu.db.handlers.GetUserDataRequestHandler;
import ru.itvitality.sbrf.cu.front.FrontendService;
import ru.itvitality.sbrf.cu.front.FrontendServiceImpl;
import ru.itvitality.sbrf.cu.db.DBService;
import ru.itvitality.sbrf.cu.db.DBServiceImpl;
import ru.itvitality.sbrf.cu.front.handlers.GetUserDataResponseHandler;
import ru.itvitality.sbrf.cu.messagesystem.*;

public class MSMain {
  private static final Logger logger = LoggerFactory.getLogger(MSMain.class);

  private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
  private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

  public static void main(String[] args) throws InterruptedException {
    MessageSystem messageSystem = new MessageSystemImpl();

    MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
    DBService dbService = new DBServiceImpl();
    databaseMsClient.addHandler( MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
    messageSystem.addClient(databaseMsClient);


    MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
    FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
    messageSystem.addClient(frontendMsClient);

    frontendService.getUserData(1, data -> logger.info("got data:{}", data));

    Thread.sleep(100);
    messageSystem.dispose();
    logger.info("done");
  }
}
