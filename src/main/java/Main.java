import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {
    static TableColumn column1;
    static TableColumn column2;
    static TableColumn column3;
    static TableView table;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button button = new Button("Add a new note...");
        button.setPrefWidth(150);
        button.setLayoutX(225);
        button.setLayoutY(25);
        button.setOnAction(ActionEvent -> {
            new SecondWindow();
            SecondWindow.label.setText(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        });

        column1 = new TableColumn("№");
        column1.setPrefWidth(50);
        column1.setStyle("-fx-alignment: CENTER");

        column2 = new TableColumn("Date/Time");
        column2.setPrefWidth(150);
        column2.setStyle("-fx-alignment: CENTER");

        column3 = new TableColumn("Notes");
        column3.setPrefWidth(350);

        table = new TableView();
        table.getColumns().addAll(column1, column2, column3);
        table.setLayoutX(30);
        table.setLayoutY(85);

        Pane pane = new Pane();
        pane.setPrefSize(600, 500);
        pane.getChildren().addAll(button, table);
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        stage.setScene(new Scene(pane));
        stage.setTitle("Simple Notes");
        stage.setResizable(false);
        stage.show();

        try {
            DataBase.connDB();
            DataBase.createDB();
            DataBase.readDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest(event -> {
            try {
                DataBase.closeDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}