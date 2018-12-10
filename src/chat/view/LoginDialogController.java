package chat.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import chat.MainApp;

public class LoginDialogController {

	@FXML
	private TextField nomeTextField;

	private Stage dialogStage;
	private boolean entrarClick = false;

	public LoginDialogController() {
	}

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean entrarClick() {
		return entrarClick;
	}

	@FXML
	private void ButtonCancelarClick() {
		dialogStage.close();
	}

	@FXML
	private void ButtonEntrarClick() {
		if (validacao()) {
			entrarClick = true;
		}
	}

	private boolean validacao() {
		String errorMessage = "";

		if (nomeTextField.getText() == null || nomeTextField.getText().length() == 0) {
			errorMessage += "Nome inválido!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija os campos inválidos");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {

	}

}
