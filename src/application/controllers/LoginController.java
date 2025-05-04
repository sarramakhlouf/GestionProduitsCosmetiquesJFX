package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("1234")) {
            errorLabel.setText("");
            System.out.println("Connexion réussie !");
        } else {
            errorLabel.setText("Identifiants incorrects");
        }
    }
}
