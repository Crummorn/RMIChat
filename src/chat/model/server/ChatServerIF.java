package chat.model.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.model.client.ChatClientIF;

public abstract interface ChatServerIF extends Remote {
	/*
	 * Regista um novo cliente no servidor.
	 * Este metodo é utilizado pelo ChatOvewViewController no metodo "initializeChatClient"
	 */
	public void registryChatClient(ChatClientIF chatClient) throws RemoteException;

	/*
	 * Espalha mensagem pelos clientes.
	 * Este metodo é utilizado pelo ChatOverViewController no metodo "ButtonEnviarClick"
	 */
	public void broadcastMessage(String message) throws RemoteException;
	
}
