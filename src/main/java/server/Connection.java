package server;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable{
    private Server server;
    private ServerSocket serverSocket;

    public Connection(Server server) {
        this.server = server;
        try {
            this.serverSocket = new ServerSocket(server.getPort());
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Override
    public void run() {
        while (true){
            try{
                Socket sockNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
                newClient.setId(server.getNumClients());
                server.addClient(newClient);

                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            }catch (Exception e){
                System.out.println(e);
            }


        }
    }
}
