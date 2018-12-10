package chat.model.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientIF extends Remote {
	
	public void retriveMessage(String message) throws RemoteException;
	
	public String dataHoraAtual() throws RemoteException;
	
}
