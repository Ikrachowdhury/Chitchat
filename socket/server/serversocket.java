package server;

import java.io.*;  
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList; 

public class serversocket extends Thread {

    public ArrayList<clinthandaler2> allclint_object = new ArrayList();
    public static ArrayList<String> clint_list = new ArrayList<String>();

    public ServerSocket sserversocket;
    public Socket socketclint;
    public DataInputStream recievingstream; 
    public DataOutputStream sendingstream; 
    public Thread threadforeachclint, incommingsocketthread, clintlistcreatethread;
    public int clintnumber = 0;

    public ArrayList<clinthandaler2> gethandalerlist() {
        return allclint_object;
    }

    public serversocket() {
        try {
            sserversocket = new ServerSocket(666);

        } catch (IOException e) { 
        }

    }

    @Override

    public void run() {

        while (true) {
            try { 
                
                socketclint = sserversocket.accept();//ps this while loop is for new incoming socket not in comming msg
                sendingstream = new DataOutputStream(socketclint.getOutputStream());        //the new incomming msg is handaled by clinthandaler handaler thread class for each socket
                recievingstream =new DataInputStream(socketclint.getInputStream());  
                clinthandaler2 clint = new clinthandaler2(this, clintnumber, socketclint, recievingstream, sendingstream); 
                threadforeachclint = new Thread(clint);
                allclint_object.add(clint);
                threadforeachclint.start();
                clintnumber++;

            } catch (Exception e) {
                System.out.println(e + "incommingsocketthread");
            }
        }
    }

}
