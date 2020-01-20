package ru.itvitality.sbrf.cu.db.handlers;

import ru.itvitality.sbrf.cu.app.common.Serializers;
import ru.itvitality.sbrf.cu.db.DBService;
import ru.itvitality.sbrf.cu.messagesystem.Message;
import ru.itvitality.sbrf.cu.messagesystem.MessageType;
import ru.itvitality.sbrf.cu.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {
  private final DBService dbService;

  public GetUserDataRequestHandler(DBService dbService) {
    this.dbService = dbService;
  }

  @Override
  public Optional<Message> handle( Message msg) {
    long id = Serializers.deserialize(msg.getPayload(), Long.class);
    String data = dbService.getUserData(id);
    return Optional.of(new Message(msg.getTo(), msg.getFrom(), Optional.of(msg.getId()), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
  }
}
