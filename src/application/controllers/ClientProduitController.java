package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientProduitController {

    @FXML
    private TableView<Produit> produitTable;

    @FXML
    private TableColumn<Produit, String> colNom;

    @FXML
    private TableColumn<Produit, Double> colPrix;

    @FXML
    private TableColumn<Produit, String> colCategorie;

    @FXML
    private TextField quantiteField;

    // Une liste observable des produits disponibles
    private ObservableList<Produit> produitsDisponibles = FXCollections.observableArrayList();

    // Une liste représentant le panier
    private ObservableList<LignePanier> panier = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialisation des colonnes de la table
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        // Exemple de produits disponibles
        produitsDisponibles.addAll(
            new Produit("Pomme", 1.2, "Fruits"),
            new Produit("Pain", 0.9, "Boulangerie"),
            new Produit("Lait", 1.5, "Produits laitiers")
        );

        produitTable.setItems(produitsDisponibles);
    }

    @FXML
    private void ajouterAuPanier() {
        Produit produitSelectionne = produitTable.getSelectionModel().getSelectedItem();
        if (produitSelectionne == null) {
            showAlert("Veuillez sélectionner un produit.");
            return;
        }

        int quantite;
        try {
            quantite = Integer.parseInt(quantiteField.getText());
            if (quantite <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert("Quantité invalide.");
            return;
        }

        panier.add(new LignePanier(produitSelectionne, quantite));
        showAlert("Produit ajouté au panier !");
    }

    @FXML
    private void afficherPanier() {
        StringBuilder contenu = new StringBuilder("Votre panier :\n");
        for (LignePanier ligne : panier) {
            contenu.append("- ")
                .append(ligne.getProduit().getNom())
                .append(" x ")
                .append(ligne.getQuantite())
                .append("\n");
        }
        showAlert(contenu.toString());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Classe modèle interne pour les produits
    public static class Produit {
        private String nom;
        private double prix;
        private String categorie;

        public Produit(String nom, double prix, String categorie) {
            this.nom = nom;
            this.prix = prix;
            this.categorie = categorie;
        }

        public String getNom() {
            return nom;
        }

        public double getPrix() {
            return prix;
        }

        public String getCategorie() {
            return categorie;
        }
    }

    // Classe pour représenter une ligne dans le panier
    public static class LignePanier {
        private Produit produit;
        private int quantite;

        public LignePanier(Produit produit, int quantite) {
            this.produit = produit;
            this.quantite = quantite;
        }

        public Produit getProduit() {
            return produit;
        }

        public int getQuantite() {
            return quantite;
        }
    }
}

