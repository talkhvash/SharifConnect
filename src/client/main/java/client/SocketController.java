package client.main.java.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.request.Request;
import shared.response.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketController extends Thread {
    private final Socket socket;
    private final Scanner scanner;
    private final PrintWriter printer;
    private final Gson gson;

    private String authToken;

    public SocketController(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printer = new PrintWriter(socket.getOutputStream(), true);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .create();
    }

    public Response sendRequest(Request request) throws Exception {
        request.setAuthToken(authToken);
        String send = gson.toJson(request);
        printer.println(send);
        String receive = scanner.nextLine();
        return gson.fromJson(receive, Response.class);
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void disconnect() {
        try {
            scanner.close();
            printer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
