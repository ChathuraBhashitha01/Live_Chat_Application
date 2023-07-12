package lk.ijse.liveChatApplication.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public Button btnLogin;

    static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName=txtUserName.getText();
        txtUserName.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/chat_room_form.fxml"))));
        stage.close();
        stage.centerOnScreen();
        stage.show();

    }

    public void txtUserNameOnAction(ActionEvent actionEvent) throws IOException {
        userName=txtUserName.getText();
        txtUserName.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/chat_room_form.fxml"))));
        stage.close();
        stage.centerOnScreen();
        stage.show();
    }
}
