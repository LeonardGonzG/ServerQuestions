package Controller.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketListener {

    public static final String SERVER_VERSION = "1.0.1";
    private int thePort = 0;

    public SocketListener(int newPort) {
        thePort = newPort;
    }

    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        boolean isRunning = true;
        //SocketController socketCtrl = null;
       // String command = "";
        CommandProcessor cmdProc = new CommandProcessor();

        try {
            serverSocket = new ServerSocket(thePort);
        } catch (IOException ex) {
            //Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (serverSocket != null) {
          do{
                try {
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    //Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    final SocketController socketCtrl = new SocketController(socket);

                    socketCtrl.start(() -> {
                        String command1 = "";
                        socketCtrl.writeText("BES Server - Leonardo Gonzalez");
                        command1 = socketCtrl.readText();
                       
                       // while (!command1.trim().toLowerCase().equals("quit")) {
                            socketCtrl.writeText(cmdProc.responseCommand(command1));
                        //    command1 = socketCtrl.readText();
                        //}
                        try {
                            socketCtrl.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } catch (IOException ex) {
                   // Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
                }

            } while (isRunning); 

        }
    }

}
