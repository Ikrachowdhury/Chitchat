 
package clint;
 
import static clint.clintsocket.ServerPort;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
//import server.clinthandaler;

public class  file_name_transfer extends clintsocket{  
    
     
     public  file_name_transfer( String filename,String name)
     { 
         this.username=name;
      setconnectiontoserver(); 
     }
     
     @Override
     public void setconnectiontoserver(){
                                       
    //stting connection 
    try{
         ip = InetAddress.getLocalHost();
         socket = new Socket(ip, ServerPort);  
         //System.out.println(ip);
         //sending name to server
         //creating resource 
         sendingstream = new DataOutputStream(socket.getOutputStream()); 
         sendingstream.writeUTF(username+" **%#@*"); 
         socket.close();
         
         }
    catch( Exception e){
           System.out.println(e+"clintsocket");
        }  
     } 
   
}
