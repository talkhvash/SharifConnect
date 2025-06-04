package client.main.java.controller;

import client.main.java.constants.Constants;
import com.google.gson.Gson;
import shared.config.Config;
import shared.request.messenger.SendMessageRequest;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Archive {
    private static final String archivePath = new Config(Constants.CONFIG).getProperty(String.class, "archivePath");
    private static Archive instance;
    public static Archive getInstance() {
        if (instance == null) instance = new Archive();
        return instance;
    }

    private final Gson json = new Gson();

    public LinkedList<SendMessageRequest> getList() {
        LinkedList<SendMessageRequest> output = new LinkedList<>();
        try {
            File file = new File(archivePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                SendMessageRequest request = json.fromJson(str, SendMessageRequest.class);
                output.add(request);
            }
            scanner.close();
            file.createNewFile();
            return output;
        } catch (IOException ioException) {
            return output;
        }
    }

    public void add(SendMessageRequest request) {
        try {
            File file = new File(archivePath);
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            json.toJson(request, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
