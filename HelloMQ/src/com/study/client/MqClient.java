package com.study.client;

import com.study.server.BrokerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 客户端调用服务端
 * @author jiayq
 */
public class MqClient {

    public static void produce(String msg) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream())

        ) {
            out.println(msg);
            out.flush();
        }
    }

    public static String consume() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("CONSUME");
            out.flush();
            String msg = in.readLine();
            return msg;

        }
    }

}
class ProduceClient{

    public static void main(String[] args) throws IOException {
        MqClient.produce("hello World");
    }

}

class ConsumeClient{

    public static void main(String[] args) throws IOException {
        System.out.println(MqClient.consume());
    }

}