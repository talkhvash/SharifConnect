package client.main.java.view.FXML.messenger.chatroom;

import client.main.java.view.GUIController;
import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;
import shared.request.messenger.SendMessageRequest;

import java.util.LinkedList;

public class ChatroomListener {

    public void listen(Integer contactId,
                        Integer chatId,
                        LinkedList<Integer> contactsId,
                        Rand rand,
                        Year year,
                        Degree degree,
                        String fileBase64,
                        String text) {

        SendMessageRequest request = new SendMessageRequest(contactId, chatId,contactsId, rand, year,
                degree, fileBase64, text);
        GUIController.getController().getListener().listen(request);
    }

}
