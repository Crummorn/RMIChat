package chat.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.MainApp;
import chat.model.client.ChatClient;
import chat.model.server.ChatServerIF;

public class ChatOverviewController {
	// @FXML
	// private TableView<ChatClientIF> usuariosTable;
	// @FXML
	// private TableColumn<ChatClientIF, String> usuariosColumn;

	@FXML
	private TextField mensagemTextField;
	@FXML
	private TextArea chatTextArea;
	// @FXML
	// private Button enviarButton;
	// @FXML
	// private Button atualizarButton;

	// Reference to the main application.
	@SuppressWarnings("unused")
	private MainApp mainApp;
	// Referencia para o nome
	private String nome;
	
	private ChatServerIF chatServer;
	
	private ChatClient client;

	/**
	 * O construtor. O construtor é chamado antes do método inicialize().
	 */
	public ChatOverviewController() {

	}

	/**
	 * Inicializa a classe controller. Este método é chamado automaticamente após o
	 * arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {		
		
	}

	@FXML
	private void ButtonEnviarClick() throws RemoteException {
//		chatTextArea.setText(chatTextArea.getText() + "\n" + mensagemTextField.getText());
		chatServer.broadcastMessage(client.getName() + ": " + mensagemTextField.getText());
		
		mensagemTextField.clear();
	}

	@FXML
	private void ButtonAtualizarClick() {
		mensagemTextField.setText(nome);
	}
	
	public void inicializarChatclient(String nome) throws MalformedURLException, RemoteException, NotBoundException {
		String chatServerURL = "rmi://localhost:10099/RMIChatServer";

		chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
		
		client = new ChatClient(nome, chatServer, this);
		
		new Thread(client).start();
	}
	
	public void retrive(String mensagem) {
		chatTextArea.setText(chatTextArea.getText() + "\n" + mensagem);		
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
