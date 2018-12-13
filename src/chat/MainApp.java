package chat;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.view.ChatOverviewController;
import chat.view.LoginDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ChatOverviewController chatOverviewController;
	private LoginDialogController loginDialogController;

	@Override
	public void start(Stage primaryStage) throws NotBoundException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("RMI CHAT");
		this.primaryStage.setMinHeight(550);
		this.primaryStage.setMinWidth(600);
		
		// Sempre que um cliente sair da aplicação ele chama o closeClient();
		this.primaryStage.setOnCloseRequest(event -> {
			try {
				chatOverviewController.closeClient();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		
		String nome = showLoginDialog();

		if (!nome.equals("")) {
			initializeRootLayout();
			showChatOverview(nome);
		} else {
			primaryStage.close();
		}
		
	}

	/**
	 * Inicializa o root layout (layout base).
	 * É chamado pelo metodo "start"
	 */
	public void initializeRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o chat overview.
	 * É chamado pelo metodo "start"
	 */
	public void showChatOverview(String nome) throws NotBoundException {
		try {
			// Carrega o ChatOverview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChatOverview.fxml"));
			AnchorPane chatOverview = (AnchorPane) loader.load();

			// Define o ChatOverview dentro do root layout.
			rootLayout.setCenter(chatOverview);

			// Dá ao controlador acesso à the main app.
			chatOverviewController = loader.getController();
			chatOverviewController.setMainApp(this);
			chatOverviewController.initializeChatClient(nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Mostra o dialogo de login.
	 * É chamado pelo metodo "start"
	 */
	public String showLoginDialog() {
		try {
			// Carrega o arquivo fxml e cria um novo stage para a janela popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Cria o palco dialogStage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Chat Login");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Define a pessoa no controller.
			loginDialogController = loader.getController();
			loginDialogController.setDialogStage(dialogStage);

			// Mostra a janela e espera até o usuário fechar.
			dialogStage.showAndWait();

			return loginDialogController.getNomeText();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/*
	 * Fecha a aplicação.
	 * É chamado pelo ChatOverviewController.
	 */
	public void close() {
		primaryStage.close();
	}

	/**
	 * Retorna o palco principal.
	 * É chamado pelo ChatOverviewController.
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
