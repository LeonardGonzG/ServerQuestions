package Controller.UI;

import Controller.Server.SocketListener;

/**
 *
 * @author Leonardo González G.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SocketListener sockListener = null;
        int port = -1;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                
                System.out.println("Error: Invalid port number");
                return;
            }
        }else{
            System.out.println("Error: invalid parameters");
            return;
        }
        sockListener = new SocketListener(port);
        sockListener.run();

    }

}
