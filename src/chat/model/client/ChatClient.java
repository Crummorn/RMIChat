package chat.model.client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import chat.model.server.ChatServerIF;
import chat.view.ChatOverviewController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChatClient extends UnicastRemoteObject implements ChatClientIF, Runnable {

	private static final long serialVersionUID = 1L;
	private ChatServerIF chatServer;
	private String name = null;
	private ChatOverviewController controller;
	
	public ChatClient(String name, ChatServerIF chatServer, ChatOverviewController controller) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		this.controller = controller;
		
		chatServer.registerChatClient(this);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void retriveMessage(String message) throws RemoteException {		
		controller.retrive(dataHoraAtual() + message);
	}
	
	@Override
	public ArrayList<ChatClientIF> getClientesOnline() throws RemoteException {
		return chatServer.getClientes();
	}		

	@Override
	public ArrayList<String> listarClientesOnline() throws RemoteException {
		return chatServer.listarClientes();
	}

	@Override
	public String dataHoraAtual() {
		Date dataHoraAtual = new Date();
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		
		return "[" + hora + " " + data + "] ";
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
