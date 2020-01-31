package com.divanxan.server;

import com.divanxan.data.Account;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static com.divanxan.service.ResponseService.requestPreparation;

public class ClientSession implements Runnable {

    private Socket socket;
    private Set<Account> users;
    private InputStream in = null;
    private OutputStream out = null;

    public ClientSession(Socket socket, Set<Account> users) throws IOException {
        this.users = users;
        this.socket = socket;
        initialize();
    }

    private void initialize() throws IOException {
        // assign streams
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())) {

            // log receive information
            System.out.println();
            StringBuilder builder = new StringBuilder();
            while (input.ready()) {
                builder.append((char) input.read());
            }
            String rez = builder.toString();
            if(!rez.isEmpty()) {
                System.out.println(rez);
                // send response
                output.println(requestPreparation(builder.toString()));
                output.flush();
            }
            System.out.println();
            System.out.println("Client disconnected!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
