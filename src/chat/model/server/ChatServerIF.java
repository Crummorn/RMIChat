package chat.model.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.model.client.ChatClientIF;

public abstract interface ChatServerIF extends Remote {
	/*
	 * Regista um novo cliente no servidor.
	 * Este metodo � utilizado pelo ChatOvewViewController no metodo "initializeChatClient"
	 */
	public void registryChatClient(ChatClientIF chatClient) throws RemoteException;
	
	/*
	 * Remove o cliente do servidor.
	 * Este metodo � utilizado sempre que um cliente sai da aplica��o
	 */
	public void removeChatClient(ChatClientIF chatClient) throws RemoteException;
	
	/*
	 * Espalha mensagem pelos clientes.
	 * Este metodo � utilizado pelo ChatOverViewController no metodo "ButtonEnviarClick"
	 */
	public void broadcastMessage(String message) throws RemoteException;
	
}
