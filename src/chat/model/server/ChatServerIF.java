package chat.model.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import chat.model.client.ChatClientIF;

public interface ChatServerIF extends Remote {
	public void registerChatClient(ChatClientIF chatClient) throws RemoteException;

	public void broadcastMessage(String message) throws RemoteException;
	
	public ArrayList<String> listarClientes() throws RemoteException;
	
	public ArrayList<ChatClientIF> getClientes() throws RemoteException;

}
