package lk.ijse.liveChatApplication;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Live Chat");
        stage.centerOnScreen();
        stage.show();
    }
}
