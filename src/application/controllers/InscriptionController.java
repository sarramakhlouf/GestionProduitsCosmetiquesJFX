package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InscriptionController {
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ChoiceBox<String> roleChoice;

    @FXML
    private void initialize() {
        roleChoice.getItems().addAll("admin", "employe");
    }

    @FXML
    private void handleSignUp() {
        System.out.println("Inscription de: " + nomField.getText());
    }
}