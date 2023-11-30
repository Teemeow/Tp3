package client;

import common.Message;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.PublicKey;

public class Client {
    private String address;
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public int getPort() {
        return port;
    }

    public Client(String address, int port){
        this.address = address;
        this.port = port;
        try{
            this.socket = new Socket(address, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }catch(Exception e){
            System.out.println(e);
        }

        Thread threadClientSend = new Thread(new ClientSend(this.out,this.socket));
        threadClientSend.start();

        Thread threadClientReceive = new Thread(new ClientReceive(this, this.socket));
        threadClientReceive.start();

    }

    public void disconnectedServer(){
        try{
            this.out.close();
            this.socket.close();
            if (this.in != null){
                this.in.close();
            }
            System.exit(0);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Message messageReceived(Message message){
        System.out.println(message);
        return message;
    }
}
