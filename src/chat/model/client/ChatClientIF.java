package chat.model.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientIF extends Remote {
	/*
	 * Retorna o nome.
	 * É utilizado pelo ChatOverviewController na hora de enviar uma mensagem.
	 */
	public String getName() throws RemoteException;

	/*
	 * Retorna data e hora atual
	 * É utilizado por esta classe para retornar a mensagem com data e hora.
	 */
	public String actualHourDate() throws RemoteException;

	/*
	 * Printa a mensagem recebida.
	 * É utilizado por está classe para printar no TextArea do ChatOverviewController
	 */
	public void retriveMessage(String message) throws RemoteException;
}
