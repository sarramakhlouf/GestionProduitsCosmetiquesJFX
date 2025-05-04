package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import application.models.Produit;

import java.net.URL;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

    @FXML private TextField nomField;
    @FXML private TextField descriptionField;
    @FXML private TextField prixField;
    @FXML private TextField stockField;

    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button removeBtn;

    @FXML private TableView<Produit> produitTable;
    @FXML private TableColumn<Produit, String> nomColumn;
    @FXML private TableColumn<Produit, String> descriptionColumn;
    @FXML private TableColumn<Produit, Double> prixColumn;
    @FXML private TableColumn<Produit, Integer> stockColumn;
    @FXML private TableColumn<Produit, String> categorieColumn;

    @FXML private ChoiceBox<String> categorieChoiceBox;

    private ObservableList<String> categories = FXCollections.observableArrayList(
        "Soins Visage", "Soins Corps", "Maquillage", "Parfumerie"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        categorieChoiceBox.setItems(categories);
        categorieChoiceBox.setValue(categories.get(0));

        addBtn.setOnAction(e -> addProduct());
        updateBtn.setOnAction(e -> updateProduct());
        removeBtn.setOnAction(e -> removeSelectedProduct(e));
    }

    public void removeSelectedProduct(ActionEvent event) {
        Produit selectedProduit = produitTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            produitTable.getItems().remove(selectedProduit);
            clearFields(); 
        } else {
            showAlert("Veuillez sélectionner un produit à supprimer.");
        }
    }

	public void addProduct() {
        String nom = nomField.getText().trim();
        String description = descriptionField.getText().trim();
        String prix = prixField.getText().trim();
        String stock = stockField.getText().trim();
        String categorie = categorieChoiceBox.getValue(); // obtenir la valeur du ChoiceBox

        if (nom.isEmpty() || description.isEmpty() || prix.isEmpty() || stock.isEmpty() || categorie == null) {
            showAlert("Tous les champs doivent être remplis !");
        } else {
            try {
                double prixValue = Double.parseDouble(prix);
                int stockValue = Integer.parseInt(stock);
                Produit newProduit = new Produit(nom, description, prixValue, stockValue, categorie);
                produitTable.getItems().add(newProduit);
                clearFields();
            } catch (NumberFormatException ex) {
                showAlert("Prix et stock doivent être des valeurs numériques !");
            }
        }
    }

    public void updateProduct() {
        Produit selectedProduit = produitTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            try {
                String prixText = prixField.getText().trim();
                String stockText = stockField.getText().trim();
                
                if (prixText.isEmpty() || stockText.isEmpty()) {
                    showAlert("Prix et stock ne peuvent pas être vides !");
                    return;
                }

                selectedProduit.setNom(nomField.getText().trim());
                selectedProduit.setDescription(descriptionField.getText().trim());

                try {
                    selectedProduit.setPrix(Double.parseDouble(prixText));
                } catch (NumberFormatException ex) {
                    showAlert("Le prix doit être une valeur numérique !");
                    return;
                }

                try {
                    selectedProduit.setStock(Integer.parseInt(stockText));
                } catch (NumberFormatException ex) {
                    showAlert("Le stock doit être une valeur numérique entière !");
                    return;
                }

                selectedProduit.setCategorie(categorieChoiceBox.getValue());
                produitTable.refresh();
                clearFields();

            } catch (Exception ex) {
                showAlert("Une erreur est survenue lors de la mise à jour du produit.");
            }
        } else {
            showAlert("Veuillez sélectionner un produit à modifier.");
        }
    }
    

    private void clearFields() {
        nomField.clear();
        descriptionField.clear();
        prixField.clear();
        stockField.clear();
        categorieChoiceBox.setValue(categories.get(0)); 
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
