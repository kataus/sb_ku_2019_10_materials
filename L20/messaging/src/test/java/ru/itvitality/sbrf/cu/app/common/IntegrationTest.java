package ru.itvitality.sbrf.cu.app.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itvitality.sbrf.cu.front.FrontendService;
import ru.itvitality.sbrf.cu.front.FrontendServiceImpl;
import ru.itvitality.sbrf.cu.front.handlers.GetUserDataResponseHandler;
import ru.itvitality.sbrf.cu.messagesystem.*;
import ru.itvitality.sbrf.cu.db.DBService;
import ru.itvitality.sbrf.cu.db.handlers.GetUserDataRequestHandler;
import ru.itvitality.sbrf.cu.messagesystem.*;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntegrationTest {
  private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

  private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
  private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
  private static final String HANDLER_MAPPING_USER_DATA = "userData";


  private MessageSystem messageSystem;
  private FrontendService frontendService;
  private MsClient databaseMsClient;
  private MsClient frontendMsClient;


  @BeforeEach
  public void setup() {
    logger.info("setup");
    messageSystem = new MessageSystemImpl();

    databaseMsClient = spy(new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem));
    DBService dbService = mock(DBService.class);
    when(dbService.getUserData(any(Long.class))).thenAnswer(invocation -> String.valueOf((Long)invocation.getArgument(0)));
    databaseMsClient.addHandler( MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
    messageSystem.addClient(databaseMsClient);

    frontendMsClient = spy(new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem));
    frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
    messageSystem.addClient(frontendMsClient);

    logger.info("setup done");
  }


  @DisplayName("Базовый сценарий получения данных")
  @RepeatedTest(1000)
  public void getDataById() throws Exception {
    int counter = 3;
    CountDownLatch waitLatch = new CountDownLatch(counter);

    IntStream.range(0, counter).forEach(id ->
        frontendService.getUserData(id, data -> {
          assertThat(data).isEqualTo(String.valueOf(id));
          waitLatch.countDown();
        }));

    waitLatch.await();
    messageSystem.dispose();
    logger.info("done");
  }

  @DisplayName("Выполнение запроса после остановки сервиса")
  @RepeatedTest(1000)
  public void getDataAfterShutdown() throws Exception {
    messageSystem.dispose();

    CountDownLatch waitLatchShutdown = new CountDownLatch(1);

    Mockito.reset(frontendMsClient);
    when(frontendMsClient.sendMessage(any(Message.class))).
        thenAnswer(invocation -> {
          waitLatchShutdown.countDown();
          return null;
        });

    frontendService.getUserData(5, data -> logger.info("data:{}", data));
    waitLatchShutdown.await();
    boolean result = verify(frontendMsClient).sendMessage(any(Message.class));
    assertThat(result).isFalse();

    logger.info("done");
  }
}
