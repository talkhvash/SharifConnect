package client.main.java.view.FXML.edu.demand.selectunit;

import client.main.java.db.ModelLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.model.edu.course.Course;
import shared.model.edu.demand.SelectUnitDemand;
import shared.model.edu.user.User;

public class SelectUnitDemandListItemFXML {
    private final SelectUnitDemandListListener listener = new SelectUnitDemandListListener();
    @FXML
    private Label studentLabel,
            courseLabel;

    @FXML
    private Button addButton,
            rejectButton;

    private int id;
    public void initialize(SelectUnitDemand demand, boolean answerView) {
        id = demand.getId();
        User user = ModelLoader.getLoader().loadUser(demand.getDemanderId());
        studentLabel.setText(user.getName());
        Course course = ModelLoader.getLoader().loadCourse(demand.getCourseId());
        courseLabel.setText(course.getName());
        addButton.setVisible(answerView);
        rejectButton.setVisible(answerView);
    }

    @FXML
    private void add()  {
        listener.listen(id, true);
    }
    @FXML
    private void reject()  {
        listener.listen(id, false);
    }
}
