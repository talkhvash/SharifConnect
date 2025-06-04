package client.main.java.client;

import shared.request.Request;

public interface RequestListener {
    void listen(Request request);
}
