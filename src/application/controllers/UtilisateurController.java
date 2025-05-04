package application.controllers;



import application.models.Utilisateur;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class UtilisateurController {

    @FXML private TableView<Utilisateur> utilisateurTable;
    @FXML private TableColumn<Utilisateur, Integer> idCol;
    @FXML private TableColumn<Utilisateur, String> nomCol, prenomCol, emailCol, roleCol;

    @FXML private TextField nomField, prenomField, emailField;
    @FXML private PasswordField mdpField;
    @FXML private ComboBox<String> roleCombo;

    private final ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
    private int nextId = 1;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        prenomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        roleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRole()));

        roleCombo.setItems(FXCollections.observableArrayList("admin", "employé"));
        utilisateurTable.setItems(utilisateurs);
    }

    public void ajouterUtilisateur() {
        Utilisateur u = new Utilisateur(
            nextId++,
            nomField.getText(),
            prenomField.getText(),
            emailField.getText(),
            mdpField.getText(),
            roleCombo.getValue()
        );
        utilisateurs.add(u);
        resetChamps();
    }

    public void modifierUtilisateur() {
        Utilisateur selected = utilisateurTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNom(nomField.getText());
            selected.setPrenom(prenomField.getText());
            selected.setEmail(emailField.getText());
            selected.setMotDePasse(mdpField.getText());
            selected.setRole(roleCombo.getValue());

            utilisateurTable.refresh();
        }
    }

    public void supprimerUtilisateur() {
        Utilisateur selected = utilisateurTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            utilisateurs.remove(selected);
        }
    }

    public void resetChamps() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        mdpField.clear();
        roleCombo.setValue(null);
    }
}

