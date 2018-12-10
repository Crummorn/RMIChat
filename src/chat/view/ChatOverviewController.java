package chat.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import chat.MainApp;
import chat.model.client.ChatClient;
import chat.model.server.ChatServerIF;
import chat.model.server.Client;

public class ChatOverviewController {
	@FXML
	private TableView<Client> usuariosTable = new TableView<>();
	@FXML
	private TableColumn<Client, String> usuariosColumn = new TableColumn<>("nome");

	@FXML
	private TextField mensagemTextField;
	@FXML
	private TextArea chatTextArea;

	// Reference to the main application.
	@SuppressWarnings("unused")
	private MainApp mainApp;

	private ChatServerIF chatServer;

	private ChatClient cliente;

	private ArrayList<Client> clientes = new ArrayList<>(); // TODO: Botão de sair que remove o cliente que saiu da lista.

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
		usuariosColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		mensagemTextField.setOnKeyPressed( (keyEvent) -> {  
		    if(keyEvent.getCode() == KeyCode.ENTER) {
				try {
					ButtonEnviarClick();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		    }
		} );
		
		mensagemTextField.requestFocus();
	}

	@FXML
	private void ButtonEnviarClick() throws RemoteException {
		chatServer.broadcastMessage(cliente.getName() + ": " + mensagemTextField.getText());

		mensagemTextField.clear();
		
		mensagemTextField.requestFocus();
	}

	@FXML
	private void ButtonAtualizarClick() throws RemoteException {
		usuariosTable.getItems().clear();

		usuariosTable.getItems().addAll(clientes);
	}

	public void adicionarCliente(String nome) {
		
	}
	
	public void inicializarChatclient(String nome) throws MalformedURLException, RemoteException, NotBoundException {
		try {
			
			String chatServerURL = "rmi://localhost:10099/RMIChatServer";

			chatServer = (ChatServerIF) Naming.lookup(chatServerURL);

			cliente = new ChatClient(nome, chatServer, this);

			new Thread(cliente).start();
			
			clientes.add(new Client(nome));
		} catch (ConnectException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("SERVIDOR OFFLINE!");
			alert.setHeaderText("O servidor encontra-se offline.");
			alert.setContentText("Por favor, tente novamente mais tarde.");

			alert.showAndWait();

			mainApp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
