package com.divanxan.service;

import java.math.BigDecimal;

public class ResponseServiceImpl {
    public static String requestPreparation(String request) {
        boolean ifGet = request.contains("GET");

        if (ifGet) {
            return processingGetRequest(request);
        } else return processingPostRequest(request);

    }

    private static String processingGetRequest(String request) {
        String line = request.substring(5, request.indexOf("HTTP")).trim();
        if (line.equals("favicon.iso")) return "";
        else return
                "HTTP/1.1 200 OK\n" +
                        "Content-Type: text/html; charset=utf-8\n\n" +
                        "<form action=\"http://localhost:8080\" method=\"POST\">\n" +
                        "  <div>\n" +
                        "    <label for=\"from\">Number account from</label>\n" +
                        "    <input name=\"from\" id=\"from\" value=\"0\">\n" +
                        "  </div>\n" +
                        "  <div>\n" +
                        "    <label for=\"to\">Number account to</label>\n" +
                        "    <input name=\"to\" id=\"to\" value=\"0\">\n" +
                        "  </div>\n" +
                        "  <div>\n" +
                        "    <label for=\"Amount\">Amount</label>\n" +
                        "    <input name=\"amount\" id=\"amount\" value=\"0\">\n" +
                        "  </div>\n" +
                        "  <div>\n" +
                        "    <button>Send</button>\n" +
                        "  </div>\n" +
                        "</form>";
    }

    private static String processingPostRequest(String request) {
        String data = request.substring(request.lastIndexOf("\n") + 1).trim();
        long from = Long.parseLong(data.substring(5, data.indexOf('&')));
        long to = Long.parseLong(data.substring(data.indexOf('&') + 4, data.lastIndexOf('&')));
        BigDecimal amount = new BigDecimal(data.substring(data.lastIndexOf('=') + 1).trim());
        return AccountServiceImpl.moneyTransfers(from, to, amount);
    }

}
