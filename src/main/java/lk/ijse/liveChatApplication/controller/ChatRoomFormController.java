package lk.ijse.liveChatApplication.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;

public class ChatRoomFormController extends Thread{
    public Button btnSend;
    public Button btnPhotos;
    public Button btnEmoji;
    public AnchorPane emoji_pane;
    public Label lblName;
    public TextArea txtMsgArea;
    public TextField txtMsg;
    public VBox vBox;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    private FileChooser fileChooser;
    private File filePath;

    public void initialize(){
        lblName.setText(LoginFormController.userName);

    }
    public void btnSendOnAction(ActionEvent actionEvent) {

    }

    public void btnPhotosOnAction(ActionEvent actionEvent) {

    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
        emoji_pane.setVisible(true);
    }
}
