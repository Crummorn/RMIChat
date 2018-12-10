package chat.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import chat.MainApp;

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
	private MainApp mainApp;

	/**
	 * O construtor. O construtor � chamado antes do m�todo inicialize().
	 */
	public ChatOverviewController() {

	}

	/**
	 * Inicializa a classe controller. Este m�todo � chamado automaticamente ap�s o
	 * arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {
		// Inicializa a tablea de pessoa com duas colunas.
		// usuariosColumn.setCellValueFactory(cellData ->
		// cellData.getValue().firstNameProperty());
	}

	@FXML
	private void ButtonEnviarClick() {
		chatTextArea.setText(chatTextArea.getText() + "\n" + mensagemTextField.getText());
		mensagemTextField.clear();
	}

	@FXML
	private void ButtonCoelhoClick() {
		mainApp.showLoginDialog();
	}

	@FXML
	private void ButtonAtualizarClick() {
	}

	/**
	 * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
