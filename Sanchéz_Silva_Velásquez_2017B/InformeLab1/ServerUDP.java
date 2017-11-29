/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author USRGAM
 */
public class ServerUDP {
    public static int PORT= 9091;
    
    public static void main (String [] args) throws IOException{
        DatagramSocket serverSocket = new DatagramSocket(PORT); //crea el socket del servidor 
        //con el mismo PUERTO del proceso con el que cliente hizo el request
        System.err.println("Server listening on port "+ PORT + " using UDP connection");
        long initialTime = System.currentTimeMillis(); //para medir cuanto tiempo se demora 
        System.out.println("Tiempo Inicial: "+initialTime+"\n");
        
        try{
            while(true){
                //Receive packet: el servidor recibe el paquete que sollicita el cliente
                byte bufferReceive[] = new byte[128];
                DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
                serverSocket.receive(receivePacket);
                //Información del puerto del cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort(); //el puerto que se creó cuando el cliente hizo el request, el cual se crea aleatoriamente
                System.out.println("Client port: "+clientPort+"\n");
                                
                //Send Packet: la respuesta del servidor al cliente
                String msg = "Message from Michelle";
                byte bufferSend[] = msg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, clientAddress, clientPort);
                //extraje la dirección del cliente y su puerto para enviar el paquete de respuesta
                serverSocket.send(sendPacket);
            }
        }finally{
            serverSocket.close();
        }
    }
            
}
