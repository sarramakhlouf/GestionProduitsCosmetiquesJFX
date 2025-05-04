package application.controllers;



import application.models.Categorie;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class CategorieController {

    @FXML private TableView<Categorie> categorieTable;
    @FXML private TableColumn<Categorie, Integer> idCol;
    @FXML private TableColumn<Categorie, String> nomCol;
    @FXML private TextField nomField;

    private final ObservableList<Categorie> categories = FXCollections.observableArrayList();
    private int nextId = 1;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));

        categorieTable.setItems(categories);
    }

    public void ajouterCategorie() {
        String nom = nomField.getText();
        if (!nom.isEmpty()) {
            Categorie cat = new Categorie(nextId++, nom);
            categories.add(cat);
            nomField.clear();
        }
    }

    public void supprimerCategorie() {
        Categorie selected = categorieTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            categories.remove(selected);
        }
    }
}
