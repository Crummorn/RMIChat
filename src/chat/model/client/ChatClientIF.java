package chat.model.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientIF extends Remote {

	public String getName() throws RemoteException;

	public String dataHoraAtual() throws RemoteException;

	public void retriveMessage(String message) throws RemoteException;

}
