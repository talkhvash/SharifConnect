package server.main.java;

import server.main.java.controller.server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        new Server(8080).run();
    }
}
