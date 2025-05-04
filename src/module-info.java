module GestionProduitsCosmetiquesJFX {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
    opens application.views to javafx.fxml;
    opens application.controllers to javafx.fxml;
    exports application;
}
