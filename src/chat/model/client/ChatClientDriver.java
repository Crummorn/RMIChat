package chat.model.client;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.model.server.ChatServerIF;

public class ChatClientDriver {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		String chatServerURL = "rmi://localhost:10099/RMIChatServer";

		ChatServerIF chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
		
		new Thread(new ChatClient(args[0], chatServer)).start();

	}

}
