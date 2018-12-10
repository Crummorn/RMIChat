package chat.model.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.model.client.ChatClientIF;

public abstract interface ChatServerIF extends Remote {
	public void registerChatClient(ChatClientIF chatClient) throws RemoteException;

	public void broadcastMessage(String message) throws RemoteException;
	
}
