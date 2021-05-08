
 package ui;
 
import clint.clintsocket;
import filedata.objectread;
import java.io.IOException;
import server.serversocket; 

public class uimain {
    public static void main(String[] args)  {
       
       // createaccountWindow crw =new createaccountWindow();
      //personalchatroomui pcr=new personalchatroomui(null,null,null);
   
     
    // serverroom serverui=new serverroom();
     serversocket server= new serversocket();
     server.start();
      
      loginwindow login=new loginwindow();
    }
    }
   
   

