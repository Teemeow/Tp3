package client;

import common.Message;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceive implements Runnable {

    private Client client;
    private Socket socket;
    private ObjectInputStream in;

    public ClientReceive(Client client, Socket socket){
        this.client = client;
        this.socket = socket;
    }
    @Override
    public void run() {
        try{
            in = new ObjectInputStream(socket.getInputStream());
            boolean isActive = true ;
            while(isActive) {
                Message mess = (Message) in.readObject();
                if (mess != null) {
                    this.client.messageReceived(mess);
                } else {
                    isActive = false;
                }
            }
            client.disconnectedServer();
        }catch(EOFException fe){
                System.out.println(fe);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
