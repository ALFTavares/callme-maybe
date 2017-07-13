package client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 23/06/17.
 */
public final class Navigation {
    private static Navigation instance = null;
    private LinkedList<Scene> scenes = new LinkedList<Scene>();
    private Map<String, Initializable> controllers = new HashMap<>();
    private Stage stage;

    private Navigation() {

    }

    public synchronized static Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadScreen(String view) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource( "/view/" + view + ".fxml"));
            Parent root = loader.load(); //load the view, instantiate the controller, call initialize

            controllers.put(view, loader.<Initializable>getController());

            Scene scene = new Scene(root);
            scenes.push(scene);

            setScene(scene);
        } catch (IOException e) {
            System.out.println("Failure to load view " + view);
        }
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void back() {
        if (scenes.size() == 1) {
            return;
        }

        scenes.pop();

        setScene(scenes.peek());
    }

    public void close() {
        stage.close();
    }
}
