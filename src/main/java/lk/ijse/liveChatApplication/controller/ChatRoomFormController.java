package lk.ijse.liveChatApplication.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

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
    File filePath;
    EventObject mouseEvent;

    public void initialize(){
        lblName.setText(LoginFormController.userName);
        try {
            socket = new Socket("localhost",4500);
            System.out.println(LoginFormController.userName+" Connected with server!");
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream(),true);

            this.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run(){
        try {
            String masagefromClients;
            while (true) {
                masagefromClients=reader.readLine();



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
        String msg = txtMsg.getText();
        writer.println(lblName.getText() + ": " + msg);

        txtMsg.clear();
        if(msg.equals("logout")||msg.equals("LOGOUT")){
            System.exit(0);
        }

    }

    public void btnPhotosOnAction(ActionEvent actionEvent) {

    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
        emoji_pane.setVisible(true);
    }

    public void txtMsgOnAction(ActionEvent actionEvent) {

    }
}
