/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotherChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javafx.scene.control.TextArea;

/**
 *
 * @author renel
 */
public class ChatListener implements Runnable {
//object declarations
    Socket socketLis = new Socket();
    TextArea txtAreaLis = new TextArea();
    
    public ChatListener(Socket sock, TextArea ta){
        socketLis = sock;
        txtAreaLis = ta;
    }
    
    @Override
    public void run() {
        try{
            InputStreamReader isr = new InputStreamReader(socketLis.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while(true){
                txtAreaLis.appendText(br.readLine()+"\n");   
            }
        }catch(Exception ex){
            System.out.println("Error in chat listener");
        }
    }
    
    
}
