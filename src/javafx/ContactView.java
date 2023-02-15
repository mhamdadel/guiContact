/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javafx;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

//import java.awt.Rectangle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author 7oda
 */
public class ContactView extends Application {

    // Rectangle rect;
    Text helloworld;
    StackPane rootpane;
    int curContact = 0;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        DB db = new DB();
        List<ContactPerson> res = db.getAll();
//        try {
//            DB.con();
//        } catch (SQLException e ){
//            
//        }
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label name = new Label("name");
        grid.add(name, 0, 1);

        TextField nameBox = new TextField();
        grid.add(nameBox, 1, 1);

        Label email = new Label("Email ID");
        grid.add(email, 0, 2);

        TextField emailBox = new TextField();
        grid.add(emailBox, 1, 2);

        Label cellPhone = new Label("cell phone");
        grid.add(cellPhone, 0, 3);

        TextField cellPhoneBox = new TextField();
        grid.add(cellPhoneBox, 1, 3);

        nameBox.setText(res.get(curContact).getName());
        emailBox.setText(res.get(curContact).getEmail());
        cellPhoneBox.setText(res.get(curContact).getCellPhone());

        Button firstBtn = new Button("First");
        firstBtn.setOnAction(e -> {
            curContact = 0;
            nameBox.setText(res.get(curContact).getName());
            emailBox.setText(res.get(curContact).getEmail());
            cellPhoneBox.setText(res.get(curContact).getCellPhone());
        });

        Button prevBtn = new Button("prev");
        prevBtn.setOnAction(e -> {
            curContact = (curContact-1) < 0 ? res.size() - 1 : (curContact-1);
            nameBox.setText(res.get(curContact).getName());
            emailBox.setText(res.get(curContact).getEmail());
            cellPhoneBox.setText(res.get(curContact).getCellPhone());
        });
        
        
        Button nextBtn = new Button("next");
        nextBtn.setOnAction(e -> {
            curContact = (curContact + 1) > res.size() - 1 ? 0 : (curContact+1);
            nameBox.setText(res.get(curContact).getName());
            emailBox.setText(res.get(curContact).getEmail());
            cellPhoneBox.setText(res.get(curContact).getCellPhone());
        });
        
        
        Button lastBtn = new Button("last");
        lastBtn.setOnAction(e -> {
            curContact = res.size() - 1;
            nameBox.setText(res.get(curContact).getName());
            emailBox.setText(res.get(curContact).getEmail());
            cellPhoneBox.setText(res.get(curContact).getCellPhone());
        });
        
        Button updateBtn = new Button("update");
        updateBtn.setOnAction(e -> {
            curContact = res.size() - 1;
            try {
                db.setByName("email", emailBox.getText() , nameBox.getText());
                System.out.println(emailBox.getText());
                System.out.println(nameBox.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ContactView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(firstBtn, prevBtn, nextBtn, lastBtn, updateBtn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
