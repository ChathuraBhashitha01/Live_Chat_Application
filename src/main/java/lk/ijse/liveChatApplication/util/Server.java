package lk.ijse.liveChatApplication.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4500);

        Socket accept;


        while (true) {

            System.out.println("Waiting for Client ...");
            accept = serverSocket.accept();
            System.out.println("Client Connected");
            ClientHandler clientThread = new ClientHandler(accept, clients);
            clients.add(clientThread);
            clientThread.start();
        }
    }
}
