package ui;

import clint.clintsocket;
import static clint.clintsocket.sendingstream;
import clint.clintthread;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Sharefilechatroomui extends clintsocket {
    //frame elements

    private JPanel jpanel1, jpanel2, jpanel3, jpanel301, jpanel201;
    private JLabel dplabel, dplabelclint, logolabel, connectedfriendslabel, namelabel;
    private JButton gamingzonebutton, filebutton, picturebutton, sendbutton, findpeoplebutton, emojibutton, clintnamebuttton, logout_button, friendship_statusbutton;
    private JScrollPane allmsgscrollpane, usermsgscrollpane, connectedfriendsscrollpane;
    private JMenuBar shareditemmenubar;
    private JMenu shareditemmenu;
    private JMenuItem file, picture;
    private JTextPane allmsgtextpane, usermsgtextpane;
    public ImageIcon dp, dpclint;
    public File file_of_friend, selected_picture_tosend, selected_file_tosend;
    public JFileChooser Picture_Chooser, file_chooser;
    public Image picture_tosend;
    public ImageIcon picture_tosend_icon;

    //imp other data type
    public String nameclint, clintname, clint, previousclint, previousmsg, personwhosent_numberstring, actualmsg, clintnumber_string, personwhosent_name, friendrequestname;
    public int clintnumber, current_clintnumber = -1, personwhosent_number, friendrequestnumber;
    public Thread clintlist, clintchathead, msgchecker, threadclint;
    //File objectfilepath;
    //public clintsocket clintname;
    //public clintthread threadclass; //which will be created with clint name
    public static Vector<clintthread> clint_listofclintthreadclass = new Vector<>();
    public ArrayList<String> Friend_list = new ArrayList<String>();
    static int logout;
    public boolean clintnamebuttton_clicked = false, flag;

    //frame  and global images
    JFrame frame = new JFrame();

    Image logobackground = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\backgroundlogo.jpg");
    Image logobackgroundscaled = logobackground.getScaledInstance(200, 200, Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon logo = new ImageIcon(logobackgroundscaled);

    Image serverdp = logobackground.getScaledInstance(50, 100, Image.SCALE_SMOOTH);

    public Sharefilechatroomui(ImageIcon dp, String name, File objectfilepath) {

        //super(name,objectfilepath); //if clitnsocket class has perameter
        this.name = name;
        this.dp = dp;
        this.objectfilepath = objectfilepath;
        dpclint = new ImageIcon(serverdp);

        nameclint = new String();
        file_of_friend = new File("friendlistof " + name + ".txt");

        setconnectiontoserver();
        fromFile_toFriendlist();
        framekeypersonalchatroom();

        //setting frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 20, 1000, 950);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\iconlogo.png");
        frame.setIconImage(icon);
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

    public void framekeypersonalchatroom() {

        logout = 0;
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getRootPane().setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder3(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        jpanel1 = new JPanel();

        jpanel2 = new JPanel();

        jpanel2.setBackground(Color.WHITE);
        jpanel201 = new JPanel();
        jpanel201.setBackground(Color.WHITE);
        jpanel201.setLayout(new FlowLayout(FlowLayout.LEFT));

        jpanel3 = new JPanel();
        jpanel301 = new JPanel();
        jpanel301.setLayout(new BoxLayout(jpanel301, BoxLayout.PAGE_AXIS));

        //create components 
        //jpanel1 component       
        dplabel = new JLabel(dp);
        dplabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        namelabel = new JLabel(name);
        namelabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        namelabel.setForeground(Color.white);

        logout_button = new JButton("Log out");
        logout_button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        logout_button.setForeground(Color.white);
        logout_button.setBackground(new Color(1, 51, 20));
        logout_button.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        connectedfriendslabel = new JLabel("Connected People");
        connectedfriendslabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        connectedfriendslabel.setForeground(Color.white);
        connectedfriendslabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        connectedfriendsscrollpane = new JScrollPane(jpanel301);
        connectedfriendsscrollpane.setPreferredSize(new Dimension(200, 300));

        //jpanel2 cpmponent 
        allmsgtextpane = new JTextPane();
        allmsgtextpane.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        allmsgtextpane.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        allmsgtextpane.setEditable(false);

        usermsgtextpane = new JTextPane();
        usermsgtextpane.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        usermsgtextpane.setEditable(false);

        allmsgscrollpane = new JScrollPane(allmsgtextpane);
        allmsgscrollpane.setPreferredSize(new Dimension(500, 500));

        usermsgscrollpane = new JScrollPane(usermsgtextpane);
        usermsgscrollpane.setPreferredSize(new Dimension(300, 30));

        sendbutton = new JButton("Send");
        sendbutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        sendbutton.setForeground(Color.white);
        sendbutton.setBackground(new Color(1, 51, 20));
        sendbutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        picturebutton = new JButton("Picture");
        picturebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        picturebutton.setForeground(Color.white);
        picturebutton.setBackground(new Color(1, 51, 20));
        picturebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        Picture_Chooser = new JFileChooser();
        file_chooser = new JFileChooser();

        filebutton = new JButton("  File ");
        filebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        filebutton.setForeground(Color.white);
        filebutton.setBackground(new Color(1, 51, 20));
        filebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //layout of jpanel1
        jpanel1.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain1 = new GridBagConstraints();
        jpanel1.setBackground(Color.BLACK);
        gridbagconstrain1.weightx = 1;
        gridbagconstrain1.weighty = 1;

        gridbagconstrain1.gridy = 0;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 20;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 5, 0);

        jpanel1.add(namelabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 1;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 20;
        gridbagconstrain1.gridwidth = 1;
        //gridbagconstrain1.fill = GridBagConstraints .VERTICAL;
        gridbagconstrain1.insets = new Insets(0, 0, 50, 0);

        jpanel1.add(dplabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 3;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(logout_button, gridbagconstrain1);

        gridbagconstrain1.gridy = 4;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(connectedfriendslabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 5;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;

        jpanel1.add(connectedfriendsscrollpane, gridbagconstrain1);

        //layout of jpanel2 
        jpanel2.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain2 = new GridBagConstraints();
        jpanel2.setBackground(Color.BLACK);

        gridbagconstrain2.gridy = 1;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.ipady = 0;//this changes the hole hight;
        gridbagconstrain2.gridwidth = 0;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 0);

        jpanel2.add(allmsgscrollpane, gridbagconstrain2);

        gridbagconstrain2.gridy = 2;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.ipady = 100;
        gridbagconstrain2.gridwidth = 6;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(usermsgscrollpane, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(picturebutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(filebutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(sendbutton, gridbagconstrain2);

        //layout of main jframe
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain = new GridBagConstraints();
        gridbagconstrain.weightx = 1;
        gridbagconstrain.weighty = 1;

        gridbagconstrain.gridx = 0;

        frame.add(jpanel1, gridbagconstrain);

        gridbagconstrain.gridx = 1;
        frame.add(jpanel2, gridbagconstrain);

        //////////////////////////////////////main functional work//////////////////////////////////////////
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    if (logout == 0) {
                        try {

                            recievingstream = new DataInputStream(socket.getInputStream());
                            msg = recievingstream.readUTF();
                            System.out.println(msg);
                           
                            //for checking either its new clintname or the main msg
                            if (msg.contains("%c@")) {

                                //deciding the messege as token 
                                StringTokenizer token = new StringTokenizer(msg, "%c@", false); //c%@ is token delimeter
                                clintnumber_string = token.nextToken();//clintnumber comes as string
                                clintname = token.nextToken();
                                clintnumber = Integer.parseInt(clintnumber_string);//converting string clintnumber into int for better matching

                                //this part is for checking if the incoming clint is already frind or not 
                                //if friend it makes friend flag true and clint is added to clint_listofclintthreadclass as friend
                                boolean friend_flag = false;
                                if (clintname != null) {
                                    for (String friend : Friend_list) {
                                        if (friend.equals(clintname)) {
                                            friend_flag = true;
                                        } else {
                                            friend_flag = false;
                                        }
                                    }
                                    // calling thread class for clint && creating object for thread to handle each clint msg
                                    clintthread clintthreadclass = new clintthread(clintname, clintnumber, friend_flag);
                                    clint_listofclintthreadclass.add(clintthreadclass);

                                    //creating button for active clint
                                    frame.setVisible(false);//refresh starts
                                    JButton button = new JButton(clintname);
                                    button.setSize(300, 300);
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
                                                    //flag is used later for providing friends special option(showing button)
                                                    //or providing friend req option
                                                }
                                            }
//
                                            if (clintnamebuttton_clicked == true) {//if any clintbutton is already clicked on we have to remove its elements from frame
                                                frame.setVisible(false);//refresh start
                                                jpanel2.remove(clintnamebuttton);
                                                jpanel2.remove(dplabelclint);

                                                if (flag == true) {//if the clint is friend
                                                    jpanel2.remove(friendship_statusbutton);
                                                    dpandnamelabel(gridbagconstrain2, flag);//this methods sets the frame according to frindship flag

                                                } else {//if the clint is not friend
                                                    dpandnamelabel(gridbagconstrain2, flag);//^
                                                    jpanel2.remove(picturebutton);
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

                                            // checking msg of current clints by checking previous created clintthread class
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
                                    jpanel2.remove(clintnamebuttton);
                                    jpanel2.remove(dplabelclint);
                                    jpanel2.remove(friendship_statusbutton);
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
                                FileOfFriendLsit(friend_name);//this method add new frind name to the friend list file
                                frame.setVisible(false);
                                jpanel2.remove(friendship_statusbutton);
                                option(gridbagconstrain2);
                                frame.setVisible(true);

                            }//if its actual msg 
                            else {
                                StringTokenizer st = new StringTokenizer(msg, "#", false);
                                actualmsg = st.nextToken();
                                personwhosent_numberstring = st.nextToken();
                                personwhosent_name = st.nextToken();
                                personwhosent_number = Integer.parseInt(personwhosent_numberstring);

                                //if the person chathead is on
                                if (personwhosent_number == current_clintnumber) {
                                    //setting msg to chatbox 
                                    allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + nameclint + " : " + actualmsg);

                                }
                                // adding msg to msg list
                                for (clintthread clint : clint_listofclintthreadclass) {
                                    if (clint.number == current_clintnumber) {
                                        clint.msgelist.add(personwhosent_name + ": " + actualmsg);
                                    }
                                }

                            }

                        } catch (IOException ex) {
                            System.out.println(ex + " personalchatroom readmsgthread");
                           // ex.printStackTrace();
                        }
                    } else {

                        break;
                    }

                }
            }
        });
        //starting msg or clint name recieve thread
        readMessage.start();

        sendbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //getting msg from textpane
                msg = usermsgtextpane.getText();

                //setting msg to chatbox
                allmsgtextpane.setText(allmsgtextpane.getText().trim() + "\n" + " You" + ": " + msg);
                for (clintthread clint : clint_listofclintthreadclass) {
                    if (clint.number == current_clintnumber) {
                        clint.msgelist.add(" You" + ": " + msg);
                    }
                }
                usermsgtextpane.setText(null);

                //sending msg to server for specific clint
                try {
                    sendingstream.writeUTF(msg + "#" + current_clintnumber);
                    //System.out.println(nameclint+"lol");
                } catch (Exception ex) {
                    System.out.println("personalchatroomui sendbutton" + ex);
                }
            }
        });

        logout_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loggingout();
            }
        });

        picturebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                picture_selection();
            }
        });

        filebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                file_selection();
            }
        });

    }

    public void loggingout() {
        try {
            logout = 1;
            sendingstream.writeUTF("logged out ,_(:);)(");

        } catch (Exception ex) {
            System.out.println("personalchatroomui loggingout" + ex);
        }
    }

    //shows dp and name for each clint name clicked
    public void dpandnamelabel(GridBagConstraints gridbagconstrain2, boolean flag) {

        allmsgtextpane.setText(null);

        //creating element to set current clits cheat head
        dplabelclint = new JLabel(dpclint);//dp got on socket 
        clintnamebuttton = new JButton(nameclint);
        clintnamebuttton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        clintnamebuttton.setForeground(new Color(1, 51, 20));
        clintnamebuttton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        friendship_statusbutton = new JButton("Friend request");
        friendship_statusbutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        friendship_statusbutton.setForeground(new Color(1, 51, 20));
        friendship_statusbutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //adding elements to jpanel2    with existing created gridbagcontant
        //this has problem as it takes maximum time to load have to wait for it 
        gridbagconstrain2.gridy = 0;
        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.gridwidth = 1;
        //gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 2, 0);

        jpanel2.add(dplabelclint, gridbagconstrain2);

        gridbagconstrain2.gridy = 0;
        gridbagconstrain2.gridx = 2;
        // gridbagconstrain2.gridwidth=1;
        gridbagconstrain2.fill = GridBagConstraints.HORIZONTAL;
        //gridbagconstrain1.insets = new Insets(0,0,0,0);

        jpanel2.add(clintnamebuttton, gridbagconstrain2);

        if (flag == false) {
            gridbagconstrain2.gridy = 0;
            gridbagconstrain2.gridx = 5;
            // gridbagconstrain2.gridwidth=1;
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
        gridbagconstrain2.gridx = 3;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.NONE;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 10);

        jpanel2.add(picturebutton, gridbagconstrain2);

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

        Picture_Chooser.setFileFilter(new FileNameExtensionFilter("Open Image", "jpg", "png", "jpeg"));
        int returnVal = Picture_Chooser.showOpenDialog(allmsgtextpane);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selected_picture_tosend = Picture_Chooser.getSelectedFile();
            picture_tosend = Toolkit.getDefaultToolkit().getImage(selected_picture_tosend.getAbsolutePath());
            Image picture_tosend_scaled = picture_tosend.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon dp1 = new ImageIcon(picture_tosend_scaled);
            allmsgtextpane.insertIcon(dp1);

        }
    }

    //select specific file from files
    public void file_selection() {
        try {
            file_chooser.setFileFilter(new FileNameExtensionFilter("Files", "txt", "TXT", "doc", "docx"));
            int returnVal = file_chooser.showOpenDialog(allmsgtextpane);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selected_file_tosend = file_chooser.getSelectedFile();

                int decission = JOptionPane.showConfirmDialog(frame, "Do you want to send the file " + selected_file_tosend.getName() + " ?");
                if (decission == JOptionPane.YES_OPTION) {
                    sendingstream.writeUTF(current_clintnumber + "#" + selected_file_tosend.getName() + "#" + selected_file_tosend.length() + "#" + " $$@^");

                    byte[] bytearray = new byte[(int) selected_file_tosend.length()];
                    FileInputStream in = new FileInputStream(selected_file_tosend);
                    in.read(bytearray);
                    sendingstream.writeInt(bytearray.length);
                    sendingstream.write(bytearray);
                    sendingstream.writeUTF("lol");
                }
                allmsgtextpane.setText(selected_file_tosend.getName());

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
            g2d.setColor(Color.BLACK);
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
            g2d.setColor(new Color(1, 51, 20));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 30, 30);
        }
    }

    private static class CustomeBorder3 extends AbstractBorder {

        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(20));
            g2d.setColor(new Color(1, 51, 20));
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
