package chat.model.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatClientIF extends Remote {
	
	public void retriveMessage(String message) throws RemoteException;
	
	public String dataHoraAtual() throws RemoteException;
	
	public String getName() throws RemoteException;

	ArrayList<ChatClientIF> getClientesOnline() throws RemoteException;
	
	public ArrayList<String> listarClientesOnline() throws RemoteException;
	
	
}
