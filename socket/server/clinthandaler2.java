package server;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class clinthandaler2 implements Runnable {

    public Socket socketclint, socket;
    public DataInputStream recievingstream;
    public DataOutputStream sendingstream;
    public String msg, name, actualmsg, recievingclint, clintname, filename;
    public serversocket server;
    public final int clintnumber;
    public int clintnumber_tosent, fileLength;
    public boolean logout = false, its_afile = false;

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
        // System.out.println(clintnumber);

    }

    //file recieving  section and sending section
    public void file_receiving_sendin() {
        try {

            for (clinthandaler2 clint : server.allclint_object) {
                ////sending incoming file notice
                if (clint.clintnumber == clintnumber_tosent) {
                    clint.sendingstream.writeUTF(filename + "#" + clintnumber + "#" + clintname + "#" + " ~%~~");

                    //file recieve
                    fileLength = recievingstream.readInt();
                    if (fileLength > 0) {
                        byte[] fileContentBytes = new byte[fileLength];
                        recievingstream.readFully(fileContentBytes, 0, fileContentBytes.length);

                        //send file
                        clint.sendingstream.writeInt(fileContentBytes.length);
                        clint.sendingstream.write(fileContentBytes);
//                File saveFile = new File("E:\\" + filename);
//                FileOutputStream fout = new FileOutputStream(saveFile);
//                fout.write(fileContentBytes);
                    }
//            //file send
//            File f=new File("D:\\2\\2.1\\All about project\\project\\ chitchat\\friendlistof ikra jojo.txt");
//            byte[] fileContentBytes = new byte[(int)f.length()];
//            FileInputStream in = new FileInputStream(f);
//            in.read(fileContentBytes);
//            sendingstream.writeInt(fileContentBytes.length);
//            sendingstream.write(fileContentBytes);
                }
            }
            its_afile = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            if (logout == false) {
                try {
                    if (its_afile == true) {
                        file_receiving_sendin();
                    } else {
                        msg = recievingstream.readUTF();
                        allfunction();
                        // System.out.println(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public void allfunction() {
        if (msg.endsWith("%c@")) {
            msg_to_actualmsg();
            toall();
            alreadyconnectedpeoplelist();
        } //for first time registering mesg
        else if (msg.endsWith("**%#@*")) {
            createregisteredclintlist(msg);
        } //for logout indication
        else if (msg.endsWith(",_(:);)(")) {
            logged_out();
        } //for incoming friend request
        else if (msg.endsWith("&&&&&&&&")) {
            msg_to_actualmsg();
            send_friendrequest();
        } //for outgoing friend request ans
        else if (msg.endsWith("^^^^^^^^")) {
            msg_to_actualmsg();
            friendrequest_ans();
        } //if its a incominf file
        else if (msg.endsWith("$$@^")) {
            its_afile = true;
            msg_to_actualmsg();
        } //if not breaks the msg
        else {
            msg_to_actualmsg();
            sendtoclint();
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

    public String msg_to_actualmsg() {

        try {
            if (msg.endsWith("%c@")) {
                StringTokenizer token = new StringTokenizer(msg, "%c@", false);

                this.clintname = token.nextToken();

            } else if (msg.endsWith("&&&&&&&&")) {

                StringTokenizer token = new StringTokenizer(msg, " &&&&&&&&", false);
                recievingclint = token.nextToken();
                clintnumber_tosent = Integer.parseInt(recievingclint);

            } else if (msg.endsWith("^^^^^^^^")) {

                StringTokenizer token = new StringTokenizer(msg, " ^^^^^^^^", false);
                recievingclint = token.nextToken();
                clintnumber_tosent = Integer.parseInt(recievingclint);
            } else if (msg.endsWith("$$@^")) {

                StringTokenizer token = new StringTokenizer(msg, "#", false);

                recievingclint = token.nextToken();
                clintnumber_tosent = Integer.parseInt(recievingclint);
                filename = token.nextToken();

            } else {
                // break the string into message and recipient part 
                StringTokenizer token = new StringTokenizer(msg, "#", false);

                actualmsg = token.nextToken();
                recievingclint = token.nextToken();
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
                    clint.sendingstream.writeUTF(actualmsg + "#" + clintnumber + "#" + clintname);
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
            for (clinthandaler2 clint : server.allclint_object) {

                clint.sendingstream.writeUTF(",_(:);)( " + clintnumber);
            }

            server.clint_list.remove(clintnumber + "%c@" + clintname);

            closingresourece();

        } catch (IOException e) {
            System.out.println(e + " clinthandaler logged_out");
        }
    }

    public void send_friendrequest() {
        try {

            for (clinthandaler2 clint : server.allclint_object) {

                if (clint.clintnumber == clintnumber_tosent) {
                    clint.sendingstream.writeUTF(clintnumber + " &&&&&&&& " + clintname);
                }
            }
        } catch (IOException e) {

        }
    }

    public void friendrequest_ans() {
        try {

            for (clinthandaler2 clint : server.allclint_object) {

                if (clint.clintnumber == clintnumber_tosent) {
                    clint.sendingstream.writeUTF(clintnumber + " ^^^^^^^^");
                }
            }
        } catch (IOException e) {

        }
    }
}
