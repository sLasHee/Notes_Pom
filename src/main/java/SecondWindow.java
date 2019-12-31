import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class SecondWindow {
    static Label label;
    static TextField textField;
    static Stage secondStage;
    SecondWindow() {
        Button button = new Button("Save new note");
        button.setLayoutX(250);
        button.setLayoutY(85);
        button.setOnAction(ActionEvent -> {
            try {
                DataBase.createDB();
                DataBase.writeToDB();
                secondStage.close();
                DataBase.readDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        label = new Label("Дата/время");
        label.setLayoutX(25);
        label.setLayoutY(10);

        textField = new TextField();
        textField.setPrefWidth(550);
        textField.setLayoutX(25);
        textField.setLayoutY(30);
        textField.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length()<=100 ? change : null));

        Pane secondPane = new Pane();
        secondPane.getChildren().addAll(label, textField, button);
        secondPane.setPrefSize(600, 125);

        secondStage = new Stage();
        secondStage.setScene(new Scene(secondPane));
        secondStage.setTitle("New note");
        secondStage.setResizable(false);
        secondStage.show();
    }
}
