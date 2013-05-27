/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jugchennai.javamoney.trakstok.navigation;

import in.jugchennai.javamoney.trakstok.javafx.Main;
import java.util.HashMap;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author gshenoy
 */
public class ScreensController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<>();
    TranslateTransition anim;
    TranslateTransition anim2;

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public boolean loadScreen(String name, String resource) {
        try {

            FXMLLoader myLoader = new FXMLLoader(Main.class.getResource(resource));

            Parent loadScreen = (Parent) myLoader.load();

            addScreen(name, loadScreen);

            ControlledScreen myScreenControler =
                    ((ControlledScreen) myLoader.getController());
            myScreenControler.setScreenParent(this);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public void setScreen(final String name, String fxmlPath) {

        boolean both = false;
        loadScreen(name, fxmlPath);

        if (screens.get(name) != null) { //screen loaded 

            if (!getChildren().isEmpty()) {
                final Node node = getChildren().get(0);

                anim2 = TranslateTransitionBuilder.create()
                        .rate(5)
                        .duration(new Duration(4000.0))
                        .node(node)
                        .fromX(0)
                        .toX(600)
                        .interpolator(Interpolator.LINEAR)
                        .autoReverse(false)
                        .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        getChildren().remove(node);

                    }
                }).build();

                both = true;
            }

            getChildren().add(0, screens.get(name));

            anim = TranslateTransitionBuilder.create()
                    .rate(5)
                    .duration(new Duration(4000.0))
                    .node(screens.get(name))
                    .fromX(-600)
                    .toX(0)
                    .interpolator(Interpolator.LINEAR)
                    .autoReverse(false)
                    .build();

            if (both) {
                anim2.play();
            } else {
                anim.play();
            }

        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
