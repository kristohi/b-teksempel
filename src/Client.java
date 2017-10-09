import java.io.*;
import java.net.*;

/**
 * Class containing methods for UDP communication
 * @author Simon, Vegar, Per-Gunnar
 */
public class Client{
    private final int port = 33097;
    private final String host = "192.168.0.103"; 
   
    private DatagramPacket sendPacket = null;
    private DatagramPacket receivePacket = null;
    DatagramSocket clientSocket = null;
    InetSocketAddress iNetAdress = null;
    
public Client(){
    iNetAdress = new InetSocketAddress(host, port);  
    try {  
          clientSocket = new DatagramSocket(port);
    }
    catch (SocketException ex) {
    }
}

/**
* Connects socket to chosen adress
*/
public void connect(){
    try {
            clientSocket.connect(iNetAdress);
    } 
    catch (SocketException ex) {
    }
}

/**
* Receives a byte array of a set size from the server
*
* @return receiveData 
*/
public byte[] receive(){
    this.connect();
    
    byte [] receiveData = new byte[6];
    
    try {    
            receivePacket = new DatagramPacket(receiveData, receiveData.length, iNetAdress.getAddress(), port);
            clientSocket.receive(receivePacket);
    } 
    catch (IOException ex) {
    }
    
    receiveData = receivePacket.getData();
    return receiveData;
}

/**
* Sends a byte array of a set size as a datagrampacket to the server
* 
* @param output     byte array that gets sent 
*/
public void send(byte[] output){
    this.connect();
    
    byte[] sendData = new byte[6];
    sendData = output;    
   
    try{
            sendPacket = new DatagramPacket(sendData, sendData.length,iNetAdress.getAddress(), port);
            clientSocket.send(sendPacket);
    } 
    catch (IOException ex) {
    }
}
}

