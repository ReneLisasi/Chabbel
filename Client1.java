/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotherChat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author renel
 */
public class Client1 extends Application implements EventHandler<ActionEvent> {
    //properties
        Button btnSend = new Button();
        Button btnClear = new Button();
        Button btnExit = new Button();
        TextField textField = new TextField();
        TextArea txtArea = new TextArea();
        Socket socket;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //make text area read only
        txtArea.setEditable(false);
        
        ScrollPane chatWndw = new ScrollPane(txtArea);

        FlowPane messageBar = new FlowPane();
        messageBar.getChildren().addAll(textField, btnSend, btnClear, btnExit);

        BorderPane root = new BorderPane();
        root.setBottom(messageBar);
        root.setCenter(chatWndw);

        Scene scene = new Scene(root, 490, 250);

        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        setCustom();
        txtArea.appendText("Connection made, chat has started... \n");
        
        socket = new Socket("localhost", 4449);
        new Thread(new ChatListener(socket, txtArea)).start();
        btnSend.setText("Send");
        btnClear.setText("Clear");
        btnExit.setText("Exit");
        btnSend.setOnAction(this); 
        btnClear.setOnAction(this);
        btnExit.setOnAction(this);
            /*
            @Override
            public void handle(ActionEvent event) {
             //send message here
                txtArea.appendText("Client: \n"+textField.getText()+"\n");
                try {
                    //call send to listener
                    sendToListener(socket, textField.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
                }
                //sendToListener
                textField.setText(null);
                //add Prinstream here
                
            }
        });*/
    }
    
        public void sendToListener(Socket socketToLis, String textFieldToLis) throws IOException{
        try{
            PrintStream ps = new PrintStream(socketToLis.getOutputStream());
            ps.println("Client: \n"+textFieldToLis);
        }catch(Exception ex){
            //catch
            System.out.println("Error in send to listener method");
        }
    }

            public void setCustom(){
        //BUTTON________________________________
        //btnSend.
        btnSend.setAlignment(Pos.BOTTOM_RIGHT);
        btnSend.setTextFill(Color.WHITE);
        btnSend.setStyle("-fx-background-color: black;-fx-font-weight: bold;");
        btnClear.setStyle("-fx-background-color: white;-fx-font-weight: bold;");
        btnExit.setStyle("-fx-background-color: lightgray;-fx-font-weight: bold;");
        btnSend.setFont(Font.font("Century Gothic"));
        btnClear.setFont(Font.font("Century Gothic"));
        btnExit.setFont(Font.font("Century Gothic"));
        if (btnSend.isHover() == true){
            btnSend.setStyle("-fx-background-color: #333333");}
        //TXT AREA________________
        txtArea.setFont(Font.font("Century Gothic", 18.0));
        txtArea.setStyle("-fx-background-color: black; -fx-padding: 1; -fx-font-size: 12");
        txtArea.setPrefHeight(600);
        txtArea.setPrefWidth(800);
        //TEXTFIELD_______________
        textField.setPrefWidth(350);
        textField.setMaxWidth(350);
        textField.setStyle("-fx-border-color: black; -fx-border-size:1");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
                        try {
                    //handle send button action
                    if(event.getSource()== btnSend){
                //send message here
                txtArea.appendText("Client: \n"+textField.getText()+"\n");
                    //call send to listener
                    sendToListener(socket, textField.getText());
                //sendToListener
                textField.setText(null);
                //add Prinstream here
                    }
                           if (event.getSource() == btnClear){
                        txtArea.clear();
                        }
                    if (event.getSource()== btnExit){
                        System.exit(0);
                    }
                        }catch (IOException ex) {
                    Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
                
            
    }
    
}}
