package Controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketListener {

    private int thePort = 0;

    public SocketListener(int newPort) {
        thePort = newPort;
    }

    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        boolean isRunning = true;
        CommandProcessor cmdProc = new CommandProcessor();

        try {
            serverSocket = new ServerSocket(thePort);
        } catch (IOException ex) {
           
        }
        if (serverSocket != null) {
            
            System.out.println("Basic Expert System Server running... :)");
          do{
                try {
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    
                }
                try {
                    final SocketController socketCtrl = new SocketController(socket);

                    socketCtrl.start(() -> {
                        String command1 = "";
                        socketCtrl.writeText("BES Server - Leonardo Gonzalez");
                        command1 = socketCtrl.readText();
                       
                        while (!command1.trim().toLowerCase().equals("quit")) {
                            socketCtrl.writeText(cmdProc.responseCommand(command1));
                            command1 = socketCtrl.readText();
                        }
                        try {
                            socketCtrl.close();
                        } catch (IOException ex) {
                             socketCtrl.writeText("Not conection");
                        }
                    });
                } catch (IOException ex) {
                    System.out.println("Error in conection");
                }

            } while (isRunning); 

        }
    }

}
