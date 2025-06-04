package server.main.java.controller.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.main.java.controller.ClientHandler;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.request.Request;
import shared.response.EmptyResponse;
import shared.response.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketController extends Thread {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printer;
    private final Gson gson;
    private final ClientHandler clientHandler;
    private boolean connected;
    private String authToken;

    public SocketController(Socket socket) {
        try {
            this.socket = socket;
            this.scanner = new Scanner(socket.getInputStream());
            this.printer = new PrintWriter(socket.getOutputStream());
            this.connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson = new GsonBuilder()
                .registerTypeAdapter(Request.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();

        clientHandler = new ClientHandler(field -> authToken = field);
    }

    @Override
    public void run() {
        while (connected) {
            try {
                String receive = scanner.nextLine();
                Request request = gson.fromJson(receive, Request.class);
                Response response;

                if (authToken != null && !authToken.equals(request.getAuthToken())) response = new EmptyResponse();
                else response = request.handle(clientHandler);

                String send = gson.toJson(response);
                printer.print(send);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("disconnect");
                kill();
            }
        }
    }

    private void kill() {
        try {
            connected = false;
            scanner.close();
            printer.close();
            socket.close();
            join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
