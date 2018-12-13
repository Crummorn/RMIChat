package chat.model.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import chat.model.client.ChatClientIF;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientIF> chatClients;
	
	/*
	 * Contrutor.
	 */
	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>(10);
	}

	@Override	
	public synchronized void registryChatClient(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.add(chatClient);
	}
	
	@Override	
	public synchronized void removeChatClient(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.remove(chatClient);
	}	

	@Override
	public synchronized void broadcastMessage(String message) throws RemoteException {
		int i = 0;

		while (i < chatClients.size()) {
			chatClients.get(i++).retriveMessage(message);
		}
	}
	
	
}
