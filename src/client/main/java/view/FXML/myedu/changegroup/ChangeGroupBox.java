package client.main.java.view.FXML.myedu.changegroup;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import client.main.java.view.FXML.myedu.Listener;

import java.util.LinkedList;

public class ChangeGroupBox {
    private Listener listener;

    public ChangeGroupBox(LinkedList<Integer> list) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("تغییر گروه");
        stage.setResizable(false);
        ChangeGroupList changeGroupList = new ChangeGroupList();
        changeGroupList.getFXML().setListener(id -> {
            listener.listen(id);
            stage.close();
        });
        changeGroupList.getFXML().refresh(list);
        stage.setScene(new Scene(changeGroupList.getPane()));
        stage.show();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
