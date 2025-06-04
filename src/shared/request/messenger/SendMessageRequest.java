package shared.request.messenger;

import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

import java.util.LinkedList;

public class SendMessageRequest extends Request {
    private final Integer contactId;
    private final Integer chatId;
    private final LinkedList<Integer> ids;
    private final Rand rand;
    private final Year year;
    private final Degree degree;
    private final String fileBase64;
    private final String text;

    public SendMessageRequest(Integer contactId,
                              Integer chatId,
                              LinkedList<Integer> contactsId,
                              Rand rand,
                              Year year,
                              Degree degree,
                              String fileBase64,
                              String text) {
        this.contactId = contactId;
        this.chatId = chatId;
        this.ids = contactsId;
        this.rand = rand;
        this.year = year;
        this.degree = degree;
        this.fileBase64 = fileBase64;
        this.text = text;
    }


    @Override
    public EmptyResponse handle(RequestHandler handler) {
        handler.sendMessage(this);
        return new EmptyResponse();
    }

    public Integer getContactId() {
        return contactId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Rand getRand() {
        return rand;
    }

    public Year getYear() {
        return year;
    }

    public Degree getDegree() {
        return degree;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public String getText() {
        return text;
    }

    public LinkedList<Integer> getIds() {
        return ids;
    }
}
