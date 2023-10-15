package com.example.silmarilcursor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    private double xOffset;
    private double yOffset;

    private boolean isMaximized = false;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene scene = new Scene(root, 1000, 600);


        Button minimizeButton = (Button) root.lookup("#minimizeButton");
        Button closeButton = (Button) root.lookup("#closeButton");
        Button maximizeButton = (Button) root.lookup("#maximizeButton");


        HBox topBar = (HBox) root.lookup("#topBar");

        topBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        topBar.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        minimizeButton.setOnAction(event -> {
            stage.setIconified(true);
        });

        maximizeButton.setOnAction(event -> {
            if (isMaximized) {
                stage.setX(xOffset);
                stage.setY(yOffset);
                stage.setWidth(1000);
                stage.setHeight(600);
            } else {
                Screen screen = Screen.getPrimary();
                stage.setX(screen.getVisualBounds().getMinX());
                stage.setY(screen.getVisualBounds().getMinY());
                stage.setWidth(screen.getVisualBounds().getWidth());
                stage.setHeight(screen.getVisualBounds().getHeight());
                xOffset = stage.getX();
                yOffset = stage.getY();
            }
            isMaximized = !isMaximized;
        });

        closeButton.setOnAction(event -> {
            stage.close();
        });

        Image icon = new Image(getClass().getResource("/com/example/silmarilcursor/assets/SilmarilCursorIco.jpg").toExternalForm());

        stage.getIcons().add(icon);
        stage.setTitle("Silmaril Cursor");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}