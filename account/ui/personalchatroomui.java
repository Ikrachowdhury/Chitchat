package ui;

import clint.clintsocket;
import filedata.chathistory;
import tictactoy.TictacMainFrame;
import dictionary.DictionaryMainFrame;
import static clint.clintsocket.sendingstream;
import clint.clintthread;
import javax.swing.*;
import java.awt.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class personalchatroomui extends clintsocket {
    //frame elements

    private JPanel jpanel1, jpanel2, jpanel301;
    private JLabel logolabel, connectedfriendslabel, clintnamelabel;
    private JButton gamingzonebutton, filebutton, picturebutton, sendbutton, logout_button, friendship_statusbutton, shareditemmenu_button, dictionary_Button;
    private JScrollPane allmsgscrollpane, usermsgscrollpane, connectedfriendsscrollpane;
    // private JMenuBar shareditemmenubar;
    // private JMenu shareditemmenu;
    // private JMenuItem file, picture, object1, object2;
    private JTextPane allmsgtextpane, usermsgtextpane;
    public ImageIcon dp, dpclint;
    public File file_of_friend, selected_picture_tosend, selected_file_tosend;
    public JFileChooser Picture_Chooser, file_chooser;
    public Image picture_tosend;
    public ImageIcon picture_tosend_icon;

    //imp other data type
    public String nameclint, clintname, clint, previousclint, previousmsg, personwhosent_numberstring, actualmsg, clintnumber_string, personwhosent_name, friendrequestname, filename, filelenth_string;
    public int clintnumber, current_clintnumber = -1, personwhosent_number, friendrequestnumber, fileLength;
    public Thread clintlist, clintchathead, msgchecker, threadclint;
    //File objectfilepath;
    //public clintsocket clintname;
    //public clintthread threadclass; //which will be created with clint name
    public static Vector<clintthread> clint_listofclintthreadclass = new Vector<>();
    public ArrayList<String> Friend_list = new ArrayList<String>();
    boolean logout;
    public boolean clintnamebuttton_clicked = false, flag, button_createfalg = true, isfile = false;

    chathistory history_chats = new chathistory();

    //frame  and global images
    JFrame frame = new JFrame();

    Image logobackground = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/user.png"));
    Image logobackgroundscaled = logobackground.getScaledInstance(200, 200, Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon logo = new ImageIcon(logobackgroundscaled);

    Image serverdp = logobackground.getScaledInstance(50, 100, Image.SCALE_SMOOTH);

    public personalchatroomui(String name, File objectfilepath) {

        //super(name,objectfilepath); //if clitnsocket class has perameter
        this.name = name;
        System.out.println(name);

        this.objectfilepath = objectfilepath;
        dpclint = new ImageIcon(serverdp);

        nameclint = new String();
        file_of_friend = new File("friendlistof " + name + ".txt");

        setconnectiontoserver();
        fromFile_toFriendlist();
        framekeypersonalchatroom();

        //setting frame
        frame.setVisible(true);
        // frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBounds(0, 20, 1035, 1050);
        frame.setResizable(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/iconlogo.png"));
        frame.setIconImage(icon);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               loggingout();
            }
        });

        //frame.pack(); 
    }

    //gets the clintname after clintbutton is clicked
    public String getclintname(String nameclint) {
        return this.nameclint = nameclint;
    }

    public int getclintnumber(int clintnumber) {
        return this.current_clintnumber = current_clintnumber;
    }

    //file recieve method
    public void recievefile() {
        try { //file recieve

            recievingstream = new DataInputStream(socket.getInputStream());

            fileLength = recievingstream.readInt();
            if (fileLength > 0) {
                byte[] fileContentBytes = new byte[fileLength];
                recievingstream.readFully(fileContentBytes, 0, fileContentBytes.length);
                File saveFile = new File("E:\\" + name + "\\" + filename);
                FileOutputStream fout = new FileOutputStream(saveFile);
                fout.write(fileContentBytes);
            }
            JOptionPane.showMessageDialog(null, "You have just recieved a File named :" + filename + " from " + personwhosent_name);
            allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + " you have recieved File " + filename);

            isfile = false;
        } catch (Exception ex) {
            System.out.println("file recieve" + ex);
        }
    }

    //frame key elements and socket work 
    public void framekeypersonalchatroom() {

        logout =false;
        frame.getContentPane().setBackground(Color.BLACK);

        jpanel1 = new JPanel();

        jpanel2 = new JPanel();

        jpanel2.setBackground(Color.WHITE);
 

        jpanel301 = new JPanel();
        BoxLayout box = new BoxLayout(jpanel301, BoxLayout.PAGE_AXIS);
        jpanel301.setLayout(box);

        //create components 
        //jpanel1 component      
//        namelabel = new JLabel(name);
//        namelabel.setFont(new Font(Font.SERIF, Font.BOLD, 40));
//        namelabel.setForeground(new Color(28, 73, 102));
        logout_button = new JButton("Log out");
        logout_button.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        logout_button.setForeground(Color.white);
        logout_button.setBackground(new Color(28, 73, 102));
        logout_button.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        shareditemmenu_button = new JButton("Shared Item");
        shareditemmenu_button.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        shareditemmenu_button.setForeground(Color.white);
        shareditemmenu_button.setBackground(new Color(28, 73, 102));
        shareditemmenu_button.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        connectedfriendslabel = new JLabel("Connected People");
        connectedfriendslabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
        connectedfriendslabel.setForeground(new Color(28, 73, 102));

        connectedfriendsscrollpane = new JScrollPane(jpanel301);
        connectedfriendsscrollpane.setPreferredSize(new Dimension(150, 400));

        gamingzonebutton = new JButton("TicTocTOy");
        gamingzonebutton.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        gamingzonebutton.setForeground(Color.white);
        gamingzonebutton.setBackground(new Color(28, 73, 102));
        gamingzonebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        dictionary_Button = new JButton("Dictionary");
        dictionary_Button.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        dictionary_Button.setForeground(Color.white);
        dictionary_Button.setBackground(new Color(28, 73, 102));
        dictionary_Button.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        logolabel = new JLabel(logo);

        allmsgtextpane = new JTextPane();
        allmsgtextpane.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        allmsgtextpane.setEditable(false);

        usermsgtextpane = new JTextPane();
        usermsgtextpane.setEditable(false);

        allmsgscrollpane = new JScrollPane(allmsgtextpane);
        allmsgscrollpane.setPreferredSize(new Dimension(500, 500));

        usermsgscrollpane = new JScrollPane(usermsgtextpane);
        usermsgscrollpane.setPreferredSize(new Dimension(300, 30));

        //send buutton background image
        Image sendbackground = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/sendlogo.png"));
        Image sendbackgroundscaled = sendbackground.getScaledInstance(50, 50, Image.SCALE_SMOOTH); //image resizer jonno
        ImageIcon sendlogo = new ImageIcon(sendbackgroundscaled);
        sendbutton = new JButton(sendlogo);
        sendbutton.setBackground(new Color(28, 73, 102));
        sendbutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        //image buutton background image
        Image picturebackground = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/picturelogo.png"));
        Image picturebackgroundscaled = picturebackground.getScaledInstance(75, 75, Image.SCALE_SMOOTH); //image resizer jonno
        ImageIcon picturelogo = new ImageIcon(picturebackgroundscaled);
        picturebutton = new JButton(picturelogo);
        picturebutton.setBackground(new Color(28, 73, 102));
        picturebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        Picture_Chooser = new JFileChooser();
        file_chooser = new JFileChooser();

        //file buutton background image
        Image filebackground = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/filelogo.png"));
        Image filebackgroundscaled = filebackground.getScaledInstance(65, 65, Image.SCALE_SMOOTH); //image resizer jonno
        ImageIcon filelogo = new ImageIcon(filebackgroundscaled);
        filebutton = new JButton(filelogo);
        filebutton.setBackground(new Color(28, 73, 102));
        filebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //layout of jpanel1
        jpanel1.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain1 = new GridBagConstraints();
        jpanel1.setBackground(new Color(237, 234, 222));
        gridbagconstrain1.ipadx =300;

        gridbagconstrain1.gridy = 1;
        gridbagconstrain1.ipady = 20;
        gridbagconstrain1.insets = new Insets(0, 0, 5, 0);

        jpanel1.add(logolabel, gridbagconstrain1);

//        gridbagconstrain1.gridy = 1;
//        //gridbagconstrain1.gridx = 1;
//       // gridbagconstrain1.ipady = 20;
//        gridbagconstrain1.gridwidth = 1; 
//        gridbagconstrain1.insets = new Insets(0,0, 5, 0);
//
//        jpanel1.add(namelabel, gridbagconstrain1);
        gridbagconstrain1.gridy = 2;
        gridbagconstrain1.insets = new Insets(0, 0, 0, 0);

        jpanel1.add(connectedfriendslabel, gridbagconstrain1);

        gridbagconstrain1.fill = GridBagConstraints.BOTH;
        gridbagconstrain1.gridy = 3;
        gridbagconstrain1.insets = new Insets(0, 0, 0, 0);
         
        jpanel1.add(connectedfriendsscrollpane, gridbagconstrain1);

        gridbagconstrain1.gridy = 4;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.insets = new Insets(5, 0, 10, 0);

        jpanel1.add(shareditemmenu_button, gridbagconstrain1);

        gridbagconstrain1.gridy = 5;
        gridbagconstrain1.insets = new Insets(0, 0, 0, 0);

        jpanel1.add(gamingzonebutton, gridbagconstrain1);

        gridbagconstrain1.gridy = 6;        
        gridbagconstrain1.insets = new Insets(0, 0, 0, 0);

        jpanel1.add(dictionary_Button, gridbagconstrain1);

        gridbagconstrain1.gridy = 7;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(logout_button, gridbagconstrain1);

        //layout of jpanel2 
        jpanel2.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain2 = new GridBagConstraints();
        jpanel2.setBackground(new Color(28, 73, 102));

        gridbagconstrain2.ipadx = 800;

        gridbagconstrain2.gridy = 1;
        gridbagconstrain2.ipady = 650;//this changes the hole hight;
        gridbagconstrain2.gridwidth = 0;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 0);

        jpanel2.add(allmsgscrollpane, gridbagconstrain2);

        gridbagconstrain2.gridy = 2;
        gridbagconstrain2.ipady = 30;
        gridbagconstrain2.gridwidth = 6;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(usermsgscrollpane, gridbagconstrain2);

        gridbagconstrain2.gridy = 3; 
        gridbagconstrain2.ipadx = 0;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 0, 0);

        jpanel2.add(sendbutton, gridbagconstrain2);

        //layout of main jframe
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain = new GridBagConstraints();
        gridbagconstrain.weightx = 1;
        gridbagconstrain.weighty = 1;

        gridbagconstrain.ipadx = 1000;
        gridbagconstrain.ipady = 1000;

        gridbagconstrain.gridx = 1;
        gridbagconstrain.gridwidth = 1; 
        
        frame.add(jpanel1, gridbagconstrain);

        gridbagconstrain.gridx = 0;
        gridbagconstrain.gridwidth = 1; 
        
        frame.add(jpanel2, gridbagconstrain);

        //end of frame work
        //starting of functional code
        //creating thread for msg or clint list recieve  //and creating clint socket
        //sending msg
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    if (logout==false) {
                        try {
                            msg = recievingstream.readUTF();

                            //for checking either its new clintname or the main msg
                            if (msg.contains("%c@")) {

                                //deciding the messege as token 
                                StringTokenizer token = new StringTokenizer(msg, "%c@", false); //c%@ is token delimeter
                                clintnumber_string = token.nextToken();//clintnumber comes as string
                                clintname = token.nextToken();
                                clintnumber = Integer.parseInt(clintnumber_string);//converting string clintnumber into int for better matching

                                //this part is for checking if the incoming clint is already frind or not 
                                //if friend it makes friend flag true and clint is added to clint_listofclintthreadclass 
                                boolean friend_flag=false;
                                if (clintname != null) {
                                    for (String friend : Friend_list) {                                      
                                        if (friend.equals(clintname)) {
                                            friend_flag = true;
                                            break;                                             
                                        }  
                                    }
                                    

                                    // calling thread class for clint && creating object for thread to handle each clint msg
                                    clintthread clintthreadclass = new clintthread(clintname, clintnumber, friend_flag);
                                    clint_listofclintthreadclass.add(clintthreadclass);

                                    //checks for history of the frinds chat
                                    boolean exists;
                                    exists = history_chats.chatfileavailability("Chat of" + clintname+name + ".txt");

                                    //if the friends chat list is available gets the chat from the history
                                    if (exists == true) {
                                        String chats;
                                        chats = history_chats.getting_chatHIdtory("Chat of" + clintname+name + ".txt");
                                        for (clintthread clint : clint_listofclintthreadclass) {
                                            if (clint.number == clintnumber) {
                                                clint.msgelist.add(chats);
                                            }
                                        }

                                    }

                                    JButton button = new JButton(clintname);
                                    button.setAlignmentX(CENTER_ALIGNMENT);
                                    button.setFont(new Font(Font.SERIF, Font.BOLD, 30));
                                    button.setForeground(Color.white);
                                    button.setBackground(new Color(28, 73, 102));
                                    frame.setVisible(false);//refresh starts                                 
                                    jpanel301.add(button);
                                    frame.setVisible(true);//refresh ends

                                    //after clintname cliclicked chatbox will get clint name and dp and other options basis of friendship
                                    button.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //
                                            usermsgtextpane.setEditable(true);
                                            //getting clintname
                                            nameclint = button.getText();
                                            getclintname(nameclint);

                                            for (clintthread clint : clint_listofclintthreadclass) {
                                                if (clint.name.equals(nameclint)) {
                                                    current_clintnumber = clint.number;
                                                    getclintnumber(current_clintnumber);
                                                    flag = clint.friend;//checks if the clicked clint is friend or not
                                                    System.out.println(flag);
                                                    //flag is used later for providing friends special option(showing button)
                                                    //or providing friend req option
                                                }
                                            }
//
                                            if (clintnamebuttton_clicked == true) {//if any clintbutton is already clicked on we have to remove its elements from frame
                                                frame.setVisible(false);//refresh start
                                                jpanel2.remove(clintnamelabel);

                                                if (flag == true) {//if the clint is friend
                                                    jpanel2.remove(friendship_statusbutton);
                                                    dpandnamelabel(gridbagconstrain2, flag);//this methods sets the frame according to frindship flag

                                                } else {//if the clint is not friend
                                                    dpandnamelabel(gridbagconstrain2, flag);//^
                                                    jpanel2.remove(picturebutton);
                                                    jpanel2.remove(filebutton);
                                                }

                                                frame.setVisible(true);//refresh ends
                                            } else {// for first time login frame
                                                usermsgtextpane.setEditable(true);
                                                //refresh start
                                                frame.setVisible(false);
                                                dpandnamelabel(gridbagconstrain2, flag);//^
                                                //refresh ends
                                                frame.setVisible(true);
                                            }
                                            clintnamebuttton_clicked = true;

                                            //another thread for checking msg of current clints by checking previous created clintthread class
                                            msgchecker = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //System.out.println(nameclint);
                                                    for (clintthread clint : clint_listofclintthreadclass) {

                                                        if (clintnumber == current_clintnumber) {

                                                            for (String oldmsg : clint.msgelist) {
                                                                //setting style         
                                                                SimpleAttributeSet attributeSet = new SimpleAttributeSet();
                                                                StyleConstants.setBold(attributeSet, true);
                                                                StyleConstants.setForeground(attributeSet, Color.BLACK);
                                                                // StyleConstants.setBackground(attributeSet, new Color(1, 51, 20));
                                                                allmsgtextpane.setCharacterAttributes(attributeSet, true);

                                                                allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + oldmsg);
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            msgchecker.setDaemon(true);
                                            msgchecker.start();

                                        }
                                    });

                                }
                            } //if the  clint goes offline ,removes the clint from clintlist
                            else if (msg.contains(",_(:);)(")) {

                                StringTokenizer token = new StringTokenizer(msg, ",_(:);)( ", false);
                                clintnumber_string = token.nextToken();
                                clintnumber = Integer.parseInt(clintnumber_string);

                                for (clintthread clint : clint_listofclintthreadclass) {

                                    if (clint.number == clintnumber) {
                                        for (int i = 0;; i++) {
                                            frame.setVisible(false);
                                            JButton button = (JButton) jpanel301.getComponent(i);
                                            String name = button.getText();
                                            if (clint.name.equals(name)) {
                                                jpanel301.remove(i);
                                                frame.setVisible(true);
                                                break;
                                            }
                                        }
                                        clint_listofclintthreadclass.remove(clint);
                                        break;
                                    }
                                }
                                //if current chathead clint goes offline 
                                if (clintnumber == current_clintnumber) {
                                    frame.setVisible(false);
                                    jpanel2.remove(clintnamelabel);
                                    jpanel2.remove(friendship_statusbutton);
                                    jpanel2.remove(picturebutton);
                                    jpanel2.remove(filebutton);
                                    allmsgtextpane.setText(null);
                                    usermsgtextpane.setEditable(false);
                                    frame.setVisible(true);
                                }

                            }//if new friend request comes
                            else if (msg.contains("&&&&&&&&")) {

                                StringTokenizer token = new StringTokenizer(msg, " &&&&&&&&", false);
                                String friendclintnumber_string = token.nextToken();
                                friendrequestname = token.nextToken();
                                friendrequestnumber = Integer.parseInt(friendclintnumber_string);

                                int decission = JOptionPane.showConfirmDialog(frame, "Do you want to be friends with " + friendrequestname + " ?");
                                if (decission == JOptionPane.YES_OPTION) {
                                    sendingstream.writeUTF(friendrequestnumber + " ^^^^^^^^");
                                    String friend_name = new String();
                                    for (clintthread clint : clint_listofclintthreadclass) {
                                        if (clint.number == friendrequestnumber) {
                                            clint.friend = true;
                                            friend_name = clint.name;
                                        }
                                    }

                                    //creates chat history file for the new friend
                                    history_chats.createHistoryFile("Chat of" + friend_name+name + ".txt");

                                    FileOfFriendLsit(friend_name);
                                    if (friendrequestnumber == current_clintnumber) {
                                        frame.setVisible(false);
                                        jpanel2.remove(friendship_statusbutton);
                                        option(gridbagconstrain2);
                                        frame.setVisible(true);

                                    }
                                }

                            }//if previously send friend request's reply comes
                            else if (msg.endsWith("^^^^^^^^")) {

                                StringTokenizer token = new StringTokenizer(msg, " ^^^^^^^^", false);
                                String friendclintnumber_string = token.nextToken();

                                String friend_name = new String();
                                friendrequestnumber = Integer.parseInt(friendclintnumber_string);

                                for (clintthread clint : clint_listofclintthreadclass) {
                                    if (clint.number == friendrequestnumber) {
                                        clint.friend = true;
                                        friend_name = clint.name;
                                    }
                                }
                                //creates chat history file for the new friend
                                history_chats.createHistoryFile("Chat of" + friend_name+name + ".txt");

                                FileOfFriendLsit(friend_name);//this method add new frind name to the friend list file
                                frame.setVisible(false);
                                jpanel2.remove(friendship_statusbutton);
                                option(gridbagconstrain2);
                                frame.setVisible(true);

                            }//if a file is coming 
                            else if (msg.endsWith("~%~~")) {
                                StringTokenizer token = new StringTokenizer(msg, "#", false);

                                filename = token.nextToken();
                                System.out.println(filename);
                                personwhosent_numberstring = token.nextToken();
                                personwhosent_name = token.nextToken();
                                personwhosent_number = Integer.parseInt(personwhosent_numberstring);
                                recievefile();
                                isfile = true;

                            } //if its actual msg 
                            else {
                                StringTokenizer st = new StringTokenizer(msg, "#", false);
                                actualmsg = st.nextToken();
                                personwhosent_numberstring = st.nextToken();
                                personwhosent_name = st.nextToken();
                                personwhosent_number = Integer.parseInt(personwhosent_numberstring);

                                //if the person chathead is on
                                if (personwhosent_number == current_clintnumber) {
                                    //setting msg to chatbox 
                                    allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n(" + nameclint + "):------->" + actualmsg);

                                }
                                //saves msg in history
                                history_chats.WriteHistory("Chat of" +personwhosent_name+name + ".txt", "(" + personwhosent_name + ")------->" + actualmsg);
                                for (clintthread clint : clint_listofclintthreadclass) {
                                    if (clint.number == personwhosent_number) {
                                        clint.msgelist.add(personwhosent_name + ": " + actualmsg);
                                    }
                                }

                            }

                        } catch (IOException ex) {
                            System.out.println(ex + " personalchatroom readmsgthread");
                        }
                    } else {

                        break;
                    }

                }
            }
        }
        );
        //starting msg or clint name recieve thread
        readMessage.start();

        sendbutton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                //getting msg from textpane
                msg = usermsgtextpane.getText();
                if (msg.equals("") == false) {
                    try {
                        if (!msg.contains("%c@") && !msg.contains(",_(:);)(") && !msg.contains("&&&&&&&&") && !msg.endsWith("^^^^^^^^") && !msg.endsWith("~%~~")) {
                            sendingstream.writeUTF(msg + "#" + current_clintnumber);
                            allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + msg + " <------:(" + name + ")");
                            usermsgtextpane.setText(null);

                            //saves msh in history
                            history_chats.WriteHistory("Chat of" +nameclint+name + ".txt", msg + " <------:(" + name + ")");

                            //sendingstream.writeUTF(msg);
                            //System.out.println(nameclint+"lol");
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry! some combinations of signs in this msg are not allowed to be paased for functional reason");
                            usermsgtextpane.setText(null);
                        }
                    } catch (Exception ex) {
                        System.out.println("personalchatroomui sendbutton" + ex);
                    }
                }
            }
        }
        );

        logout_button.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                 
               loggingout();
            }
        }
        );

        picturebutton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                picture_selection();
            }
        }
        );

        filebutton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                file_selection();
            }
        }
        );

        shareditemmenu_button.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                FileShow show = new FileShow("E:\\" + name + "\\");
                show.display();
            }
        }
        );

        gamingzonebutton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                TictacMainFrame titocgame = new TictacMainFrame();

            }
        }
        );

        dictionary_Button.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DictionaryMainFrame dictionary = new DictionaryMainFrame();
            }
        }
        );

    }

    public void loggingout() {
        try {      
            sendingstream.writeUTF("logged out ,_(:);)("); 
            logout =true;
            System.exit(0);

        } catch (Exception ex) {
            System.out.println("personalchatroomui loggingout" + ex);
        }
    }
   
    //shows dp and name for each clint name clicked
    public void dpandnamelabel(GridBagConstraints gridbagconstrain2, boolean flag) {

        allmsgtextpane.setText(null);

        //creating element to set current clits cheat head 
        clintnamelabel = new JLabel(nameclint);
        clintnamelabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        clintnamelabel.setForeground(Color.WHITE);
        clintnamelabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        Image friendrequest = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/friendlogo.png"));
        Image friendrequestscaled = friendrequest.getScaledInstance(50, 50, Image.SCALE_SMOOTH); //image resizer jonno
        ImageIcon friendrequestlogo = new ImageIcon(friendrequestscaled);
        friendship_statusbutton = new JButton(friendrequestlogo);
        friendship_statusbutton.setBackground(new Color(28, 73, 102));
        friendship_statusbutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //adding elements to jpanel2    with existing created gridbagcontant
        //this has problem as it takes maximum time to load have to wait for it 
        gridbagconstrain2.gridy = 0;
        gridbagconstrain2.gridx = 0;
        // gridbagconstrain2.gridwidth=1;
        gridbagconstrain2.fill = GridBagConstraints.HORIZONTAL;
        //gridbagconstrain1.insets = new Insets(0,0,0,0);

        jpanel2.add(clintnamelabel, gridbagconstrain2);

        if (flag == false) {
            gridbagconstrain2.gridy = 0;
            gridbagconstrain2.gridx = 1;
            //gridbagconstrain2.gridwidth=1;
            gridbagconstrain2.fill = GridBagConstraints.HORIZONTAL;
            //gridbagconstrain1.insets = new Insets(0,0,0,0);

            jpanel2.add(friendship_statusbutton, gridbagconstrain2);
        } else {
            option(gridbagconstrain2);
        }

        friendship_statusbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendingstream.writeUTF(current_clintnumber + " &&&&&&&&");
                } catch (Exception ex) {
                    System.out.println("personalchatroomui sendbutton" + ex);
                }
            }
        });

    }

    public void option(GridBagConstraints gridbagconstrain2) {

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 2;

        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.NONE;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 10);

        jpanel2.add(picturebutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 3;

        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 0);

        jpanel2.add(filebutton, gridbagconstrain2);

    }

    //frinds name to file of frinedlist to frienlist array
    public void fromFile_toFriendlist() {
        try {
            if (file_of_friend.exists()) {
                FileReader fileReader = new FileReader(file_of_friend);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Friend_list.add(line);
                     
                }
                fileReader.close();

            } else {
                file_of_friend.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //add friend name to the file of friendlist
    public void FileOfFriendLsit(String friendname) {
        try {

            if (file_of_friend.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file_of_friend, true));
                bw.write(friendname);
                Friend_list.add(friendname);
                bw.newLine();

                bw.close();
            } else {
                file_of_friend.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //select picture from file
    public void picture_selection() {
        try {
            Picture_Chooser.setFileFilter(new FileNameExtensionFilter("Open Image", "jpg", "png", "jpeg"));
            int returnVal = Picture_Chooser.showOpenDialog(allmsgtextpane);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selected_picture_tosend = Picture_Chooser.getSelectedFile();

                int decission = JOptionPane.showConfirmDialog(frame, "Do you want to send the file " + selected_picture_tosend.getName() + " ?");
                if (decission == JOptionPane.YES_OPTION) {
                    sendingstream.writeUTF(current_clintnumber + "#" + selected_picture_tosend.getName() + "#" + " $$@^");

                    byte[] bytearray = new byte[(int) selected_picture_tosend.length()];
                    FileInputStream in = new FileInputStream(selected_picture_tosend);
                    in.read(bytearray);
                    sendingstream.writeInt(bytearray.length);
                    sendingstream.write(bytearray);
                }
                allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + " " + selected_picture_tosend.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //select specific file from files
    public void file_selection() {
        try {
            file_chooser.setFileFilter(new FileNameExtensionFilter("Files", "txt", "TXT", "doc", "docx","pptx"));
            int returnVal = file_chooser.showOpenDialog(allmsgtextpane);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selected_file_tosend = file_chooser.getSelectedFile();

                int decission = JOptionPane.showConfirmDialog(frame, "Do you want to send the file " + selected_file_tosend.getName() + " ?");
                if (decission == JOptionPane.YES_OPTION) {
                    sendingstream.writeUTF(current_clintnumber + "#" + selected_file_tosend.getName() + "#" + " $$@^");

                    byte[] bytearray = new byte[(int) selected_file_tosend.length()];
                    FileInputStream in = new FileInputStream(selected_file_tosend);
                    in.read(bytearray);
                    sendingstream.writeInt(bytearray.length);
                    sendingstream.write(bytearray);
//                    int count;
//                    while ((count = in.read(bytearray)) > 0) {
//                        sendingstream.write(bytearray, 0, count);
//                    }
//                    sendingstream.write(0);
                }
                allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + " " + selected_file_tosend.getName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //design of the borders
    private static class CustomeBorder extends AbstractBorder {

        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(new Color(28, 73, 102));//dark blue
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }

    private static class CustomeBorder1 extends AbstractBorder {

        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(15));
            g2d.setColor(new Color(237, 234, 222));//white
            g2d.drawRoundRect(x, y, width - 1, height - 1, 30, 30);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final personalchatroomui other = (personalchatroomui) obj;
        if (!Objects.equals(this.nameclint, other.nameclint)) {
            return false;
        }
        if (!Objects.equals(this.clintname, other.clintname)) {
            return false;
        }
        if (!Objects.equals(this.clint, other.clint)) {
            return false;
        }
        if (!Objects.equals(this.previousclint, other.previousclint)) {
            return false;
        }
        if (!Objects.equals(this.previousmsg, other.previousmsg)) {
            return false;
        }
        if (!Objects.equals(this.personwhosent_numberstring, other.personwhosent_numberstring)) {
            return false;
        }
        if (!Objects.equals(this.actualmsg, other.actualmsg)) {
            return false;
        }
        if (!Objects.equals(other.name, other.clintname)) {
            return false;
        }
        return true;
    }

}
