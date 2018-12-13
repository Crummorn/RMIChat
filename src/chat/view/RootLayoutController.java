package chat.view;

import java.rmi.RemoteException;

import javafx.fxml.FXML;

public class RootLayoutController {

	private ChatOverviewController chatOverviewController;
	
	/*
	 * Construtor.
	 * O construtor é chamado antes do método initialize().
	 */
	public RootLayoutController() {
	}	

	@FXML
	private void initialize() {
	}

	@FXML
	private void menuButtonSairClick() throws RemoteException {
		chatOverviewController.closeClient();
		System.exit(0);		
	}

	public void setChatOverviewController(ChatOverviewController chatOverviewController) {
		this.chatOverviewController = chatOverviewController;
	}

}
