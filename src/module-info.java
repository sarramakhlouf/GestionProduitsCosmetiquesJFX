module GestionProduitsCosmetiquesJFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens application to javafx.graphics, javafx.fxml;
    opens application.views to javafx.fxml;
    opens application.models to javafx.base;
    opens application.controllers to javafx.fxml;

    exports application;
}
