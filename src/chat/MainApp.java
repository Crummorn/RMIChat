package chat;

import java.io.IOException;
import java.rmi.NotBoundException;

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

	@Override
	public void start(Stage primaryStage) throws NotBoundException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("RMI CHAT");

		initRootLayout();

		String nome = showLoginDialog();

		if (!nome.equals("")) {

			showChatOverview(nome);

		} else {
			primaryStage.close();
		}
	}

	/**
	 * Inicializa o root layout (layout base).
	 */
	public void initRootLayout() {
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
	 * Mostra o chat overview dentro do root layout.
	 * 
	 * @throws NotBoundException
	 */
	public void showChatOverview(String nome) throws NotBoundException {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChatOverview.fxml"));
			AnchorPane chatOverview = (AnchorPane) loader.load();

			// Define o person overview dentro do root layout.
			rootLayout.setCenter(chatOverview);

			// Dá ao controlador acesso à the main app.
			ChatOverviewController controller = loader.getController();

			controller.setMainApp(this);

			controller.inicializarChatclient(nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
			LoginDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Mostra a janela e espera até o usuário fechar.
			dialogStage.showAndWait();

			return controller.getNomeText();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void close() {
		primaryStage.close();
	}
	
	/**
	 * Retorna o palco principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
