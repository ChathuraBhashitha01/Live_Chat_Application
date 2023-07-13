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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
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
    public AnchorPane root;

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
        emoji_pane.setVisible(false );
    }
    @Override
    public void run(){
        try {
            String messagefromClients;
            while (socket.isConnected()) {
                messagefromClients = reader.readLine();
                String[] clients = messagefromClients.split(" ");
                String msgsender = clients[0];

                String [] msgToArrray=messagefromClients.split(" ");
                String msgContent=" ";
                for (int i = 0; i <msgToArrray.length-1; i++) {
                    msgContent += msgToArrray[i+1]+" ";
                }
                String firstChars = "";
                if (msgContent.length() > 1) {
                    firstChars = msgContent.substring(1, 4);

                }
                Text text = new Text(messagefromClients);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_LEFT);

                if (firstChars.equals("img")) {

                    msgContent=msgContent.substring(4,msgContent.length()-1);

                    File file=new File(msgContent);
                    Image image = new Image(file.toURI().toString());


                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);

                    if(!msgsender.equals(lblName.getText()+":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1=new Text(" "+msgsender+"  :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    }
                    else {
                        vBox.setAlignment(Pos.TOP_RIGHT);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        Text text1=new Text(" "+msgsender+"  :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
                else {

                    TextFlow txtFlow = new TextFlow();
                    if (!msgsender.equals(lblName.getText() + ":")) {

                        txtFlow.setStyle("-fx-color: rgb(7, 126, 217);" +
                                "-fx-background-color: rgb(222, 238, 250);" +
                                " -fx-background-radius: 10px");
                        txtFlow.setPadding(new Insets(3, 10, 3, 10));

                        txtFlow.getChildren().add(text);
                        txtFlow.setMaxWidth(200);

                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setPadding(new Insets(2, 5, 2, 10));
                        hBox.getChildren().add(txtFlow);
                    } else {
                        txtFlow.getChildren().add(text);

                        txtFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(222, 238, 250);" +
                                "-fx-background-radius: 10px");
                        txtFlow.setPadding(new Insets(3, 10, 3, 10));

                        txtFlow.setMaxWidth(200);

                        vBox.setAlignment(Pos.TOP_RIGHT);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.setPadding(new Insets(2, 5, 2, 10));
                        hBox.getChildren().add(txtFlow);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                }
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
        writer.println(lblName.getText()+": "+"img"+filePath.getPath());


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

    public void imoji_1(MouseEvent mouseEvent) {
        String emoji = new String(Character.toChars(128546));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);

    }

    public void imoji_2(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128513));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);

    }

    public void imoji_3(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128514));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_4(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128540));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_6(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(129297));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);

    }

    public void imoji_7(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128525));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_10(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128559));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_9(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128539));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_8(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128519));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_11(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128546));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_12(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128554));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_14(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128546));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_13(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128550));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_15(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128560));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }

    public void imoji_5(MouseEvent mouseEvent) {
        String emoji=new String(Character.toChars(128539));
        txtMsg.setText(emoji);
        emoji_pane.setVisible(false);


    }
}
