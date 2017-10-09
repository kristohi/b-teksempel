import java.util.Arrays;

/**
 * This class has responsibility for transfering  
 * data between PC and Odroid.
 * 
 * @author Simon, Vegar, Per-Gunnar
 */
public class OdroidData extends Thread{
    Thread OdroidThread = new Thread();
    Client UDPClient;
    byte[] boatData;
    String lastMode = "auto";
    
public OdroidData(){
    UDPClient = new Client();
    this.boatData = new byte[6];
}

/**
* This run() method gets called when the threads start() method is called
*/   
@Override
public void run(){

while(true){
 
if(PcMain.drivingModeFlag == DrivingModeFlag.AUTO){
    if(lastMode.equalsIgnoreCase("manual")){
    byte tempByte = boatData[0];
    tempByte = (byte) (tempByte | (1 << 0));
    boatData[0] = tempByte;
    sendData(boatData);   
    System.out.println(Arrays.toString(boatData));
    }
    lastMode = "auto";    
}
else if(PcMain.drivingModeFlag == DrivingModeFlag.MANUAL){
    if(lastMode.equalsIgnoreCase("auto")){
    byte tempByte = boatData[0];
//    boatData[2] = 0;
//    boatData[1] = 0;
    tempByte = (byte) (tempByte & ~(1 << 0));
    boatData[0] = tempByte;
    sendData(boatData);
    System.out.println(Arrays.toString(boatData));
    }
    lastMode = "manual";
}
    //this.boatData = getData(); // problem med asynkrone meldinger med denne linjen
}
}

/**
* Sends data to the Odroid Server
*
* @param outData the byte array that gets sent
*/
public void sendData(byte[] outData){
    this.UDPClient.send(outData);
}

/**
* Retrieves data from the Odroid server
*
* @return inData
*/
public byte[] getData(){
    byte[] odroidData;
    odroidData = this.UDPClient.receive();
    
    return odroidData;
}
}
