/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author USRGAM
 */
public class ClienteUDP {
    
    private static int SERVER_PORT = 9091; //para comunicarse entre procesos
    //Ejecuta un cliente en donde va a aparecer un mensaje de dialogo que pide la dirección IP del Servidor
    
    public static void main (String [] args) throws IOException{
        
        String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is \n" +
                "running the date service on port" + SERVER_PORT+":"); //dirección IP
        //Request: envía el paquete
        /*Para crear la conexión:
            1. crear un socket, manda el paquete
        */
        DatagramSocket clientSocket = new DatagramSocket(); //objeto que java lo entiende como un socket que crea una conexión UDP
        //el socket es el camino por donde viaja el paquete
        byte bufferSend[] = serverAddress.getBytes(); //transformo a bytes la dirección IP del servidor
        DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getByName(serverAddress), SERVER_PORT); 
        //crea el paquete a enviar
        clientSocket.send(sendPacket); //por el socket creado manda el paquete.
        
        
        //Recive paquete que manda el servidor
        byte bufferReceive[] = new byte[128];
        DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
        clientSocket.receive(receivePacket);
        
        //Transforma bytes a String
        InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
        BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
        String answer = input.readLine();
        
        //Despliega el mensaje
        JOptionPane.showMessageDialog(null, answer);
        clientSocket.close();
        System.exit(0);
        
        
    }
    
}
