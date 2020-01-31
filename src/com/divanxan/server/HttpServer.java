package com.divanxan.server;

import com.divanxan.data.Account;
import com.divanxan.service.AccountService;
import com.divanxan.service.AccountServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HttpServer {

    private static final int DEFAULT_PORT = 8080;
    private static Set<Account> users;

    public static void main(String[] args) {

        dataPreparation();

        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            System.out.println("Server started!");

            while (true) {
                // waiting for connection
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                ClientSession session = new ClientSession(socket, users);
                new Thread(session).start();
                // open streams write an read for new connections
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void dataPreparation() {
        //create concurrent collection of users
        ConcurrentHashMap<Account, Integer> userLength = new ConcurrentHashMap<>();
        users = ConcurrentHashMap.newKeySet(userLength.size());
        AccountService accountService = new AccountServiceImpl(users);
        // filling up account collections
        fillingUpUsers();
    }

    private static void fillingUpUsers() {
        Account acc1 = new Account();
        acc1.setId(1L);
        acc1.setFirstName("Ivan");
        acc1.setSecondName("Petrov");
        acc1.setEmail("ivanpetrov@gmail.ua");
        acc1.setAccount(new BigDecimal("3000.67"));

        Account acc2 = new Account();
        acc2.setId(2L);
        acc2.setFirstName("Nicolay");
        acc2.setSecondName("Ivanov");
        acc2.setEmail("nicolayivanov@gmail.ua");
        acc2.setAccount(new BigDecimal("5345.38"));

        Account acc3 = new Account();
        acc3.setId(3L);
        acc3.setFirstName("Terentii");
        acc3.setSecondName("Vladimirov");
        acc3.setEmail("terentiivladimirov@gmail.ua");
        acc3.setAccount(new BigDecimal("600.80"));

        Account acc4 = new Account();
        acc4.setId(4L);
        acc4.setFirstName("Konstantin");
        acc4.setSecondName("Zhirnov");
        acc4.setEmail("konstantinzhirnov@gmail.ua");
        acc4.setAccount(new BigDecimal("4367.66"));

        Account acc5 = new Account();
        acc5.setId(5L);
        acc5.setFirstName("Vasya");
        acc5.setSecondName("Pupkin");
        acc5.setEmail("vasyapupkin@gmail.ua");
        acc5.setAccount(new BigDecimal("11.20"));

        users.add(acc1);
        users.add(acc2);
        users.add(acc3);
        users.add(acc4);
        users.add(acc5);
    }
}
