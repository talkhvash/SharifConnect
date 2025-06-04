package server.main.java.controller.server;

import server.main.java.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(Integer port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e1) {
            e1.printStackTrace();
            try {
                this.serverSocket = new ServerSocket(Constants.PORT);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new SocketController(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
