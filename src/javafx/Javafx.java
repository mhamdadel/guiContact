/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javafx;

import javafx.scene.shape.Rectangle;

//import java.awt.Rectangle;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.stage.Stage;

/**
 *
 * @author 7oda
 */
public class Javafx extends Application {

    // Rectangle rect;
    Text helloworld;
    StackPane rootpane;

    @Override
    public void init() throws Exception {
        super.init();
        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
       
        helloworld = new Text("Hello_World");
        helloworld.setId("text");
        //Setting the effect to the text
        helloworld.setEffect(reflection);
       
        Rectangle rect = new Rectangle(0, 0, 400, 300);
        rect.setId("rect");

        rootpane = new StackPane();
        rootpane.getChildren().add(rect);
        rootpane.getChildren().add(helloworld);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(rootpane, 400, 300);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
