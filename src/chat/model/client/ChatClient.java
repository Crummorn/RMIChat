package chat.model.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import chat.model.server.ChatServerIF;
import chat.view.ChatOverviewController;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

	private static final long serialVersionUID = 1L;
	private ChatServerIF chatServer;
	private String name = null;
	private ChatOverviewController controller;
	
	/*
	 * Construtor.
	 * É utilizado nos testes feitos direto no Prompt.
	 */
	public ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		
		chatServer.registryChatClient(this);
	}
	
	/*
	 * Construtor.
	 * É utilizado nos testes feitos com interface grafica.
	 */
	public ChatClient(String name, ChatServerIF chatServer, ChatOverviewController controller) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		this.controller = controller;
		
		chatServer.registryChatClient(this);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String actualHourDate() {
		Date dataHoraAtual = new Date();
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		
		return "[" + hora + " " + data + "] ";
	}

	@Override
	public void retriveMessage(String message) throws RemoteException {		
		controller.reciveMenssage(actualHourDate() + message);
	}

	@Override
	public void run() {
		
		try {
			chatServer.broadcastMessage("Novo usuario conectado [" + this.name + "]");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		while (true) {
			
		}
	}

	
}
