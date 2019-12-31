import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static void connDB() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:MyDataC.db");
        statement = connection.createStatement();
    }

    static void createDB() throws Exception {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'MyNotes'" +
                "('id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'dateTime' VARCHAR(10) ," +
                "'note' MESSAGE_TEXT );");
    }

    static void writeToDB() throws Exception {
        statement = connection.createStatement();
        String date = SecondWindow.label.getText();
        String notes = SecondWindow.textField.getText();
        statement.execute("INSERT INTO 'MyNotes'('dateTime','note') VALUES (' "+date+" ', '"+notes+"')");
    }

    static void readDB() throws Exception {
        statement = connection.createStatement();

        ObservableList <Note> observableList = FXCollections.observableArrayList();

        resultSet = statement.executeQuery("SELECT * FROM 'MyNotes'");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String date = resultSet.getString("dateTime");
            String note = resultSet.getString("note");


            observableList.add(new Note(id, date, note));

            Main.column1.setCellValueFactory(new PropertyValueFactory<Note, Integer>("noteId"));
            Main.column2.setCellValueFactory(new PropertyValueFactory<>("noteDate"));
            Main.column3.setCellValueFactory(new PropertyValueFactory<>("noteNote"));
        }
        Main.table.setItems(observableList);
    }

    static void closeDB() throws Exception {
        if (connection != null)
            connection.close();
        if(statement != null)
            statement.close();
        if(resultSet != null)
            resultSet.close();
    }
}
