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
import java.rmi.ServerException;
import java.util.ArrayList;

import chat.MainApp;
import chat.model.client.ChatClient;
import chat.model.client.Client;
import chat.model.server.ChatServerIF;

public class ChatOverviewController {
	@FXML
	private TableView<Client> usuariosTable = new TableView<>();
	@FXML
	private TableColumn<Client, String> usuariosColumn = new TableColumn<>("nome");

	@FXML
	private TextField mensagemTextField;
	@FXML
	private TextArea chatTextArea;

	private MainApp mainApp;
	private ChatServerIF chatServer;
	private ChatClient chatClient;

	private ArrayList<Client> clientes = new ArrayList<>(); 

	/**
	 * Contrutor
	 * O construtor é chamado antes do método initialize().
	 */
	public ChatOverviewController() {

	}

	/**
	 * Inicializa a classe controller. 
	 * Este método é chamado automaticamente após o arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {
		// Inicializa a coluna da tabela
		usuariosColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		// Adiciona a tecla ENTER como confirmação
		mensagemTextField.setOnKeyPressed((keyEvent) -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				try {
					buttonEnviarClick();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		// Solicita o foco assim que inicializa no TextField
		mensagemTextField.requestFocus();
	}

	/*
	 * Envia mensagem para outros usuarios.
	 * Este metodo ocorre ao clicar no botão "Enviar" no ChatOverview.
	 */
	@FXML
	private void buttonEnviarClick() throws RemoteException {
		chatServer.broadcastMessage(chatClient.getName() + ": " + mensagemTextField.getText());
		mensagemTextField.clear();
		mensagemTextField.requestFocus();
	}

	/*
	 * Atualiza lista de usuarios online.
	 * Este metodo ocorre ao clicar no botão "Atualizar" no ChatOverview
	 */
	@FXML
	private void buttonAtualizarClick() throws RemoteException {
		usuariosTable.getItems().clear();
		usuariosTable.getItems().addAll(clientes);
	}

	/*
	 * Cria conexão do usuario com o servidor.
	 * Este metodo ocorre apos o usuario clicar no botão "Entrar" no LoginDialog.
	 */
	public void initializeChatClient(String nome) throws MalformedURLException, RemoteException, NotBoundException {
		// Tenta criar uma conexão com o ChatServer.
		try {
			String chatServerURL = "rmi://localhost:10099/RMIChatServer";
			chatServer = (ChatServerIF) Naming.lookup(chatServerURL);
			chatClient = new ChatClient(nome, chatServer, this);

			new Thread(chatClient).start();
		} catch (ConnectException e) {
			// Cria um alerta caso o ChatServer esteja offline.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("SERVIDOR OFFLINE!");
			alert.setHeaderText("O servidor encontra-se offline.");
			alert.setContentText("Por favor, tente novamente mais tarde.");
			alert.showAndWait();

			// Fecha a aplicação
			mainApp.close();
		} catch (ServerException e) {
			// Cria um alerta caso o ChatServer esteja offline.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("SERVIDOR OFFLINE!");
			alert.setHeaderText("O servidor encontra-se offline.");
			alert.setContentText("Por favor, tente novamente mais tarde.");
			alert.showAndWait();
			
			// Fecha a aplicação
			mainApp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Adiciona a mensagem recebida no TextArea
	 * É chamado pelo ChatServer pelo metodo "broadcastMessage"
	 */
	public void reciveMenssage(String mensagem) {
		chatTextArea.setText(chatTextArea.getText() + "\n" + mensagem);
	}

	/*
	 * Remove o cliente do servidor e fecha a aplicação
	 * Este metodo ocorre sempre que um cliente sai do sistema
	 */
	public void closeClient() throws RemoteException {
		chatServer.removeChatClient(chatClient);
		System.exit(0);
	}
	
	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}