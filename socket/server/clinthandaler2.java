package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class clinthandaler2 implements Runnable {
 
    public Socket socketclint, socket;
    public DataInputStream recievingstream;
    public DataOutputStream sendingstream;
    public String msg, name, actualmsg, recievingclint, clintname;
    public serversocket server;
    public final int clintnumber;
    public int clintnumber_tosent;
    public boolean  logout = false;

    public clinthandaler2(serversocket server, int clintnumber, Socket socket, DataInputStream recievingstream,
            DataOutputStream sendingstream) {
        this.server = server;
        this.clintnumber = clintnumber;
        this.recievingstream = recievingstream;
        //this.msg=msg;
        this.sendingstream = sendingstream;
        this.name = name;
        this.socket = socket;
        clintname = new String();
       //System.out.println(clintnumber);

    }

    @Override
    public void run() {

        while (true) {
            if ( logout == false) {
                try {
                    msg = recievingstream.readUTF();
                    System.out.println(msg);
                    System.out.println(clintnumber);
                    if (msg.endsWith("%c@")) {
                        msg_to_actualmsg(msg);
                        toall();
                        alreadyconnectedpeoplelist();

                    } //for first time registering mesg
                    else if (msg.endsWith("**%#@*")) {
                        createregisteredclintlist(msg);
                    } else if (msg.endsWith(",_(:);)(")) {

                        logged_out();
                    } //if notbreaks the msg
                    else {
                        msg_to_actualmsg(msg);
                        sendtoclint();
                    }
                } catch (IOException e) {

                    System.out.println(e + "clintHandeler");
                }

            } else {
                break;
            }
        }

    }

    public void closingresourece() {
        try {
            //closing resources   
            this.recievingstream.close();
            this.sendingstream.close();

        } catch (IOException e) {
        }

    }

    public void createregisteredclintlist(String registeredclint) {
        try {
            msg = registeredclint;

            StringTokenizer token = new StringTokenizer(msg, "**%#@*", false);
            String clintnameregistered = token.nextToken();
            System.out.println(clintnameregistered);
            File file = new File("D:\\2\\2.1\\project\\chitchat\\REGISTERED_PEOPLE.txt");

            if (file.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("REGISTERED_PEOPLE.txt", true));
                bw.write(clintnameregistered);
                bw.newLine();
                bw.close();
            } else {
                file.mkdirs();
            }
        } catch (IOException ex) {
            System.out.println("serversocket createregisteredclintlist" + ex);
        }
    }

    public String msg_to_actualmsg(String connectedclintname) {
        msg = connectedclintname;
        try {
            if (msg.endsWith("%c@")) {
                msg = connectedclintname;
                StringTokenizer token = new StringTokenizer(msg, "%c@", false); //c%@clintname is token delimeter

                this.clintname = token.nextToken();
            } else {
                // break the string into message and recipient part 
                StringTokenizer st = new StringTokenizer(msg, "#", false);

                actualmsg = st.nextToken();
                recievingclint = st.nextToken();
                clintnumber_tosent = Integer.parseInt(recievingclint);

            }
        } catch (Exception ex) {
            System.out.println("serversocket  createconnectedclintlist" + ex);
        }
        return this.clintname + actualmsg + recievingclint + clintnumber_tosent;

    }

    public void toall() {
        try {
            for (clinthandaler2 clint : server.allclint_object) {

                if (clint.clintnumber != clintnumber) {
                    clint.sendingstream.writeUTF(clintnumber + "%c@" + clintname);
                    //clint.sendingstream.writeUTF(clintnumber+"%c@");
                }

            }
        } catch (IOException e) {

        }
    }

    public void alreadyconnectedpeoplelist() {
        try {

            for (String alreadyconnected : server.clint_list) {
                sendingstream.writeUTF(alreadyconnected);
                //sendingstream.writeUTF(clintname+"%c@");
            }
            server.clint_list.add(clintnumber + "%c@" + clintname);
            //server.clint_list.add(clintname+"%c@");

        } catch (IOException e) {

        }

    }

    public void sendtoclint() {
        try {

            for (clinthandaler2 clint : server.allclint_object) {

                if (clint.clintnumber == clintnumber_tosent) {
                    clint.sendingstream.writeUTF(actualmsg + "#" + clintnumber+"#"+clintname);
                }
            }
        } catch (IOException e) {

        }

    }

    public void logged_out() {

        try {
           
            logout = true;
             
            for (clinthandaler2 clint : server.allclint_object) {
                
                if (clint.clintnumber == clintnumber) {

                    server.allclint_object.remove(clint);
                    break;
                }
            } 
            for (clinthandaler2 clint : server.allclint_object ) {
                
               clint.sendingstream.writeUTF(",_(:);)( "+clintnumber);
            }
 
            server.clint_list.remove(clintname);
            closingresourece();

        } catch (Exception e) {
            System.out.println(e +" clinthandaler logged_out");
        }
    }
}
