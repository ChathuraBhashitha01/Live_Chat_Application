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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
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
    FileChooser fileChooser;

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
            String messagefromClients;
            while (socket.isConnected()) {
                messagefromClients=reader.readLine();
                String [] clients=messagefromClients.split(" ");
                String msgsender=clients[0];



                Text text = new Text(messagefromClients);

                HBox hBox = new HBox(10);
                hBox.setAlignment(Pos.BOTTOM_LEFT);


//                if(msgsender.equalsIgnoreCase(lblName.getText()+":")) {
//                    vBox.setAlignment(Pos.TOP_LEFT);
//                    hBox.setAlignment(Pos.CENTER_LEFT);
//                    hBox.getChildren().add(text);
//                }else {
//                    vBox.setAlignment(Pos.TOP_LEFT);
//                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
//                    hBox.getChildren().add(text);
//                }

//                Image image = new Image(file.toURI().toString());
//
//                ImageView imageView = new ImageView(image);
//
//                imageView.setFitHeight(150);
//                imageView.setFitWidth(200);
//                hBox.getChildren().add(imageView);

                TextFlow txtFlow=new TextFlow();
                if(!msgsender.equalsIgnoreCase(lblName.getText()+":")){
                    Text txtName=new Text(msgsender+" ");
                    //txtFlow.getChildren().add(txtName);

                    txtFlow.setStyle("-fx-color: rgb(7, 126, 217);" +
                            "-fx-background-color: rgb(222, 238, 250);" +
                            " -fx-background-radius: 10px");
                    txtFlow.setPadding(new Insets(3,10,3,10));

                    txtFlow.getChildren().add(text);
                    txtFlow.setMaxWidth(200);

                    vBox.setAlignment(Pos.TOP_LEFT);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(2,5,2,10));
                    hBox.getChildren().add(txtFlow);
                }else {
                    txtFlow.getChildren().add(text);

                    txtFlow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color: rgb(222, 238, 250);" +
                            "-fx-background-radius: 10px");
                    txtFlow.setPadding(new Insets(3,10,3,10));

                    txtFlow.setMaxWidth(200);

                    vBox.setAlignment(Pos.TOP_RIGHT);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);
                    hBox.setPadding(new Insets(2,5,2,10));
                    hBox.getChildren().add(txtFlow);
                }
                Platform.runLater(() -> vBox.getChildren().addAll(hBox));

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
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath=fileChooser.showOpenDialog(stage);
        writer.println(lblName.getText()+" "+"img"+filePath.getPath());

    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
        emoji_pane.setVisible(true);
    }

    public void txtMsgOnAction(ActionEvent actionEvent) {
        String msg = txtMsg.getText();
        writer.println(lblName.getText() + ": " + msg);

        txtMsg.clear();
        if(msg.equals("logout")||msg.equals("LOGOUT")){
            System.exit(0);
        }
    }
}
