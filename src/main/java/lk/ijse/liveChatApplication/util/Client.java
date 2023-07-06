package lk.ijse.liveChatApplication.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{
    private ArrayList<Client> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(Socket socket, ArrayList<Client> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {



    }

}
