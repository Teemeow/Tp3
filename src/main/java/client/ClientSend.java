package client;

import common.Message;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable{
    private ObjectOutputStream out;
    Socket socket;

    public ClientSend(ObjectOutputStream out, Socket socket) {
        this.out = out;
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("Votre Message >>");
            String m = sc.nextLine();
            Message mess = new Message("client", m);
            try{
                out.writeObject(mess);
                out.flush();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
