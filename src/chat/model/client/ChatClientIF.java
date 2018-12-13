package chat.model.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientIF extends Remote {
	/*
	 * Retorna o nome.
	 * � utilizado pelo ChatOverviewController na hora de enviar uma mensagem.
	 */
	public String getName() throws RemoteException;

	/*
	 * Retorna data e hora atual
	 * � utilizado por esta classe para retornar a mensagem com data e hora.
	 */
	public String actualHourDate() throws RemoteException;

	/*
	 * Printa a mensagem recebida.
	 * � utilizado por est� classe para printar no TextArea do ChatOverviewController
	 */
	public void retriveMessage(String message) throws RemoteException;
}
