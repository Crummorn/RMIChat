package chat.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginDialogController {

	@FXML
	private TextField nomeTextField;

	private Stage dialogStage;

	/*
	 * Construtor.
	 * O construtor é chamado antes do método initialize().
	 */
	public LoginDialogController() {
	}	

	@FXML
	private void initialize() {
		// Adiciona a tecla ENTER como confirmação
		nomeTextField.setOnKeyPressed( (keyEvent) -> {  
		    if(keyEvent.getCode() == KeyCode.ENTER) 
					ButtonEntrarClick();
		} );

		// Solicita o foco assim que inicializa no TextField
		nomeTextField.requestFocus();
	}

	@FXML
	private void ButtonCancelarClick() {
		dialogStage.close();
	}

	@FXML
	private void ButtonEntrarClick() {
		if (validacao()) {
			dialogStage.close();
		}
	}
	
	/*
	 * Retorna o nome inserido no TextField
	 */
	public String getNomeText() {
		return nomeTextField.getText();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/*
	 * Valida se o nome usado é valido.
	 */
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
}
