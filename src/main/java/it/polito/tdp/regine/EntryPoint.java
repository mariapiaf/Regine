package it.polito.tdp.regine;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.regine.FXMLController;
import it.polito.tdp.regine.model.Regine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
          Parent root = loader.load();
          Scene scene = new Scene(root);
          
          Regine model = new Regine();
          FXMLController controller = loader.getController();
          controller.setModel(model);
          
          stage.setTitle("Gestore Corsi");
          stage.setScene(scene);
          stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
