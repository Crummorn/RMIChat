package chat.model.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ChatServerDriver {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		LocateRegistry.createRegistry(10099);
		
		try {
			ChatServer chat = new ChatServer();

			Naming.rebind("rmi://localhost:10099/RMIChatServer", chat);
						
	        System.out.println("Chat Server está pronto.");
        } catch(Exception e) {
            System.out.println("[Chat Server Error]");
            System.out.println(e.getMessage());
        }
			
	}
	
}
