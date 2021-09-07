package clint;
//import  server.serversocket;
//import filedata.objectread;   //jdi lage

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clintsocket {

    public static int ServerPort = 666;
    public InetAddress ip;
    public Socket socket;
    public DataInputStream recievingstream; 
    public static DataOutputStream sendingstream; 
    public Thread sendMessage;
    public Thread readMessage;
    public String msg, name, connectedclints, myfriendlist;
    public String clintnames, username;
    public Scanner scanner;
    public File objectfilepath, file; 
    //public ArrayList<String> clint_list; 

    //for perameteraizing need  
    public clintsocket() {
        //if needed
    }

    //setconnection
    public void setconnectiontoserver() {
        try {

            ip = InetAddress.getLocalHost();
            socket = new Socket(ip, ServerPort);
            //socket = new Socket("192.168.1.105", ServerPort);
            sendingstream = new DataOutputStream(socket.getOutputStream());
            recievingstream = new DataInputStream(socket.getInputStream());
            System.out.println(ip);
            //sending name to server
            //creating resource 

            sendingstream.writeUTF(name + " %c@");
            myfriendlist = "D:\\2\\2.1\\All about project\\project\\ chitchat\\friendlistof " + name + ".txt";
            file = new File(myfriendlist);
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e + "clintsocket");
        }
    }

    
}
