package lk.ijse.liveChatApplication.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private ArrayList<ClientHandler> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
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

            String msg;
            while (socket.isConnected()) {
                try {
                    while (socket.isConnected()) {
                        msg = reader.readLine();
                        if(msg !=null) {
                            for (ClientHandler clientHandler : clients) {
                                clientHandler.writer.write(msg);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }finally {
                    try {
                        socket.close();
                        reader.close();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }

}
