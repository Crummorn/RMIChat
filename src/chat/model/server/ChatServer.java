package chat.model.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import chat.model.client.ChatClientIF;

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {

	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientIF> chatClients;
	
	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClientIF>(10);
	}

	@Override
	public synchronized void registerChatClient(ChatClientIF chatClient) throws RemoteException {
		this.chatClients.add(chatClient);
	}

	@Override
	public synchronized void broadcastMessage(String message) throws RemoteException {
		int i = 0;

		while (i < chatClients.size()) {
			chatClients.get(i++).retriveMessage(message);
		}
	}
	
	@Override
	public ArrayList<String> listarClientes() throws RemoteException {
		ArrayList<String> lista = new ArrayList<String>();
		
		for (ChatClientIF chatClientIF : chatClients) {
			lista.add(chatClientIF.getName());
		}
		
		return lista;
	}

	@Override
	public ArrayList<ChatClientIF> getClientes() throws RemoteException {
		return this.chatClients;
	}

}
