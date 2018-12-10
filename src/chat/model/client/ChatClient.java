package chat.model.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import chat.model.server.ChatServerIF;
import chat.view.ChatOverviewController;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

	private static final long serialVersionUID = 1L;
	private ChatServerIF chatServer;
	private String name = null;
	private Scanner scanner;
	private ChatOverviewController controller;

	public ChatClient(String name, ChatServerIF chatServer) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		chatServer.registerChatClient(this);
	}
	
	public ChatClient(String name, ChatServerIF chatServer, ChatOverviewController controller) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		this.controller = controller;
		
		chatServer.registerChatClient(this);
	}

	@Override
	public void retriveMessage(String message) throws RemoteException {		
		controller.retrive(message);
	}
	

	@Override
	public String dataHoraAtual() {
		Date dataHoraAtual = new Date();
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		
		return "[" + hora + " " + data + "]";
	}

	@Override
	public void run() {
		String message;
		
		try {
			chatServer.broadcastMessage(dataHoraAtual() + " Novo usuario conectado [" + this.name + "]");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		while (true) {
			message = scanner.nextLine();

			try {
				chatServer.broadcastMessage(dataHoraAtual() + name + ": " + message);
			} catch (RemoteException e) {
				e.printStackTrace();
			} 
		}
	}

	
}
