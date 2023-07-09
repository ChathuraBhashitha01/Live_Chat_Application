package lk.ijse.liveChatApplication.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatRoomFormController extends Thread{
    public Button btnSend;
    public Button btnPhotos;
    public Button btnEmoji;
    public AnchorPane emoji_pane;
    public Label lblName;
    public TextField txtMsg;
    public VBox vBox;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public void initialize(){
        lblName.setText(LoginFormController.userName);
        try {
            socket = new Socket("localhost",4500);
            System.out.println(LoginFormController.userName+" Connected with server!");
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream(),true);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnSendOnAction(ActionEvent actionEvent) {


    }

    public void btnPhotosOnAction(ActionEvent actionEvent) {

    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
        emoji_pane.setVisible(true);
    }
}
