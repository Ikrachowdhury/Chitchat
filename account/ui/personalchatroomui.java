package ui;

import clint.clintsocket;
import static clint.clintsocket.sendingstream;
import clint.clintthread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class personalchatroomui extends clintsocket {
    //frame elements

    private JPanel jpanel1, jpanel2, jpanel3, jpanel301, jpanel201;
    private JLabel dplabel, dplabelclint, logolabel, connectedfriendslabel, namelabel;
    private JButton gamingzonebutton, filebutton, picturebutton, sendbutton, findpeoplebutton, emojibutton, clintnamebuttton, logout_button, friendship_statusbutton;
    private JScrollPane allmsgscrollpane, usermsgscrollpane, connectedfriendsscrollpane;
    private JMenuBar shareditemmenubar;
    private JMenu shareditemmenu;
    private JMenuItem file, picture, object1, object2;
    private JTextPane allmsgtextpane, usermsgtextpane;
    public ImageIcon dp, dpclint;

    //imp other data type
    public String nameclint, clintname, clint, previousclint, previousmsg, personwhosent_numberstring, actualmsg, clintnumber_string, personwhosent_name, friendrequestname;
    public int clintnumber, current_clintnumber, personwhosent_number, friendrequestnumber;
    public Thread clintlist, clintchathead, msgchecker, threadclint;
    //File objectfilepath;
    //public clintsocket clintname;
    //public clintthread threadclass; //which will be created with clint name
    public static Vector<clintthread> clint_listofclintthreadclass = new Vector<>();
    static int logout;
    public boolean clintnamebuttton_clicked = false,flag;

    //frame  and global images
    JFrame frame = new JFrame();

    Image logobackground = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\backgroundlogo.jpg");
    Image logobackgroundscaled = logobackground.getScaledInstance(200, 200, Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon logo = new ImageIcon(logobackgroundscaled);

    Image serverdp = logobackground.getScaledInstance(50, 100, Image.SCALE_SMOOTH);

    public personalchatroomui(ImageIcon dp, String name, File objectfilepath) {

        //super(name,objectfilepath); //if clitnsocket class has perameter
        this.name = name;
        this.dp = dp;
        this.objectfilepath = objectfilepath;
        dpclint = new ImageIcon(serverdp);
        nameclint = new String();

        setconnectiontoserver();

        //clintsocket clintname=new clintsocket(name,objectfilepath); 
        //clintname.setconnectiontoserver();
        //clintname.recievemessegethread();
        //System.out.println(clint.clintname);
        framekeypersonalchatroom();
        //setting frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 20, 1000, 950);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\iconlogo.png");
        frame.setIconImage(icon);
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
        /*jpanel2.setBorder (BorderFactory.createCompoundBorder(
                     new CustomeBorder3() ,new EmptyBorder(new Insets(10,10,10,10)
                     )));*/
        jpanel2.setBackground(Color.WHITE);
        jpanel201 = new JPanel();
        jpanel201.setBackground(Color.WHITE);
        jpanel201.setLayout(new FlowLayout(FlowLayout.LEFT));

        jpanel3 = new JPanel();
        jpanel301 = new JPanel();
        jpanel301.setLayout(new BoxLayout(jpanel301, BoxLayout.PAGE_AXIS));

        //create components 
        //jpanel1 component      
        /* Image displaypicture= Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\backgroundlogo.jpg");
    Image  displaypicturescaled= displaypicture.getScaledInstance(100,100,Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon  dp= new ImageIcon( displaypicturescaled);*/
        dplabel = new JLabel(dp);
        dplabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        namelabel = new JLabel(name);
        namelabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        namelabel.setForeground(Color.white);
        namelabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        findpeoplebutton = new JButton("Find PEOPLE");
        findpeoplebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        findpeoplebutton.setForeground(Color.white);
        findpeoplebutton.setBackground(new Color(1, 51, 20));
        findpeoplebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        logout_button = new JButton("Log out");
        logout_button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        logout_button.setForeground(Color.white);
        logout_button.setBackground(new Color(1, 51, 20));
        logout_button.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        shareditemmenubar = new JMenuBar();
        shareditemmenubar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new Color(1, 51, 20));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        shareditemmenu = new JMenu("Shared Item");
        shareditemmenu.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        shareditemmenu.setForeground(Color.WHITE);
        shareditemmenu.setBackground(Color.BLACK);
        shareditemmenubar.add(shareditemmenu);

        file = new JMenuItem("Files");
        file.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        file.setForeground(Color.white);
        file.setBackground(Color.BLACK);
        shareditemmenu.add(file);

        picture = new JMenuItem("Pictures");
        picture.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        picture.setForeground(Color.white);
        picture.setBackground(Color.BLACK);
        shareditemmenu.add(picture);

        logolabel = new JLabel(logo);

        //jpanel2 cpmponent 
        /*                             
    dplabelclint=new JLabel(dpclint);  
    // dplabelclint.setBorder(BorderFactory.createCompoundBorder(
                    //new CustomeBorder1() ,new EmptyBorder(new Insets(10,10,10,10)
                    //))); 
                    
    namelabelclint=new JLabel(nameclint); 
    namelabelclint.setFont(new Font(Font.SERIF, Font.BOLD,20));                 
    namelabelclint.setForeground(Color.white);                        //THIS ELEMENT WILL CHANGE TIME TO TIME SO IT WILL BE IN ITS THREAD CLASS
    namelabelclint.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder1() ,new EmptyBorder(new Insets(10,10,10,10)
                    ))); */
        allmsgtextpane = new JTextPane();
        allmsgtextpane.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));
        //set size and font
        allmsgtextpane.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        /*    //giving  style to the text
                     SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
                     StyleConstants.setBold(attributeSet, true);
                     StyleConstants.setForeground(attributeSet,Color.white );  
                     StyleConstants.setBackground(attributeSet,new Color(1,51,20));
                     StyleConstants.setAlignment(attributeSet,StyleConstants.ALIGN_RIGHT);
                     allmsgtextpane.setCharacterAttributes(attributeSet, true);*/
        allmsgtextpane.setEditable(false);

        usermsgtextpane = new JTextPane();
        usermsgtextpane.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

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

        emojibutton = new JButton("Emoji");
        emojibutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        emojibutton.setForeground(Color.white);
        emojibutton.setBackground(new Color(1, 51, 20));
        emojibutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        picturebutton = new JButton("Picture");
        picturebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        picturebutton.setForeground(Color.white);
        picturebutton.setBackground(new Color(1, 51, 20));
        picturebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        filebutton = new JButton("  File ");
        filebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        filebutton.setForeground(Color.white);
        filebutton.setBackground(new Color(1, 51, 20));
        filebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        gamingzonebutton = new JButton("Game");
        gamingzonebutton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        gamingzonebutton.setForeground(Color.white);
        gamingzonebutton.setBackground(new Color(1, 51, 20));
        gamingzonebutton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //jpanel3 component
        connectedfriendslabel = new JLabel("Connected People");
        connectedfriendslabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        connectedfriendslabel.setForeground(Color.white);
        connectedfriendslabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        connectedfriendsscrollpane = new JScrollPane(jpanel301);
        connectedfriendsscrollpane.setPreferredSize(new Dimension(200, 300));

        //layout of jpanel1
        jpanel1.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain1 = new GridBagConstraints();
        jpanel1.setBackground(Color.BLACK);
        //gridbagconstrain1.weightx=1;
        //gridbagconstrain1.weighty=1;

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

        gridbagconstrain1.gridy = 2;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(findpeoplebutton, gridbagconstrain1);

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

        jpanel1.add(shareditemmenubar, gridbagconstrain1);

        gridbagconstrain1.gridy = 5;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        //gridbagconstrain1.insets = new Insets(0,0, 0,0);

        jpanel1.add(logolabel, gridbagconstrain1);

        //layout of jpanel2 
        jpanel2.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain2 = new GridBagConstraints();
        jpanel2.setBackground(Color.BLACK);
        /*   
     gridbagconstrain2.gridy = 0; 
     //gridbagconstrain2.gridx = 1; 
     gridbagconstrain2 .ipady=50;
     gridbagconstrain2.gridwidth =6;
     gridbagconstrain2.fill = GridBagConstraints.BOTH;
     gridbagconstrain2.insets = new Insets(0,0,0,0);
      
     jpanel2.add(jpanel201,gridbagconstrain2);*/
 /*
     gridbagconstrain2.gridy = 0; 
     gridbagconstrain2.gridx = 1; 
     gridbagconstrain2.gridwidth =1;
     //gridbagconstrain2.fill = GridBagConstraints.BOTH;
     gridbagconstrain2.insets = new Insets(0,0,2,0);
      
     jpanel2.add(dplabelclint,gridbagconstrain2);
    
     gridbagconstrain2.gridy =0 ; 
     gridbagconstrain2.gridx =2; 
    // gridbagconstrain2.gridwidth=1;
     gridbagconstrain2.fill = GridBagConstraints.HORIZONTAL;
    //gridbagconstrain1.insets = new Insets(0,0,0,0);
   
     jpanel2.add(namelabelclint,gridbagconstrain2); */

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

        jpanel2.add(sendbutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 2;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 10, 0);

        jpanel2.add(emojibutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 3;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.NONE;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 10);

        jpanel2.add(picturebutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 4;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 0);

        jpanel2.add(filebutton, gridbagconstrain2);

        gridbagconstrain2.gridy = 3;
        gridbagconstrain2.gridx = 5;
        gridbagconstrain2.ipady = 0;
        gridbagconstrain2.gridwidth = 1;
        gridbagconstrain2.fill = GridBagConstraints.BOTH;
        gridbagconstrain2.insets = new Insets(0, 0, 20, 0);

        jpanel2.add(gamingzonebutton, gridbagconstrain2);

        //layout of jpanel3                              
        jpanel3.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain3 = new GridBagConstraints();
        jpanel3.setBackground(Color.BLACK);
        //gridbagconstrain3.weightx=1;
        //gridbagconstrain3.weighty=1;

        gridbagconstrain3.gridy = 0;
        //gridbagconstrain1.gridx = 1; 
        gridbagconstrain3.gridwidth = 1;
        gridbagconstrain3.fill = GridBagConstraints.CENTER;
        gridbagconstrain3.insets = new Insets(10, 0, 0, 0);

        jpanel3.add(connectedfriendslabel, gridbagconstrain3);

        gridbagconstrain3.gridy = 1;
        //gridbagconstrain1.gridx = 2; 
        gridbagconstrain3.gridwidth = 0;
        gridbagconstrain3.fill = GridBagConstraints.BOTH;
        //gridbagconstrain3.insets = new Insets(0,0,0,0);

        jpanel3.add(connectedfriendsscrollpane, gridbagconstrain3);

        //layout of main jframe
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain = new GridBagConstraints();
        gridbagconstrain.weightx = 1;
        gridbagconstrain.weighty = 1;

        gridbagconstrain.gridx = 0;
        gridbagconstrain.gridwidth = 1;

        gridbagconstrain.insets = new Insets(0, 0, 50, 20);
        frame.add(jpanel1, gridbagconstrain);

        gridbagconstrain.gridx = 1;
        gridbagconstrain.gridwidth = 1;
        gridbagconstrain.insets = new Insets(0, 0, 0, 40);
        frame.add(jpanel2, gridbagconstrain);

        gridbagconstrain.gridx = 2;
        gridbagconstrain.gridwidth = 1;
        gridbagconstrain.insets = new Insets(0, 0, 360, 0);
        frame.add(jpanel3, gridbagconstrain);

        /*
       frame.add(jpanel3,BorderLayout.EAST);  
       frame.add(jpanel1,BorderLayout.WEST);  
       frame.add( jpanel2,BorderLayout.CENTER); */
        //end of frame work
        //starting of functional code
        //creating thread for msg or clint list recieve  //and creating clint socket
        //sending msg
        readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    if (logout == 0) {
                        try {

                            recievingstream = new DataInputStream(socket.getInputStream());
                            msg = recievingstream.readUTF();
                            // System.out.println(msg+"  ashtese but jache na");
                            //for checking either its new clintname or the main msg
                            //deciding the messege as token  
                            if (msg.contains("%c@")) {

                                StringTokenizer token = new StringTokenizer(msg, "%c@", false); //c%@ is doken delimeter
                                clintnumber_string = token.nextToken();
                                clintname = token.nextToken();

                                clintnumber = Integer.parseInt(clintnumber_string);

                                if (clintname != null) {
                                    // calling thread class for clint && creating object for thread to handle each clint msg
                                    clintthread clintthreadclass = new clintthread(clintname, clintnumber);
                                    clint_listofclintthreadclass.add(clintthreadclass);

                                    frame.setVisible(false);
                                    //creating button for active clint
                                    JButton button = new JButton(clintname);
                                    button.setSize(300, 300);
                                    jpanel301.add(button);
                                    frame.setVisible(true);

                                    //after clintname cliclicked chatbox will getting clint name and dp
                                    button.addActionListener(new ActionListener() {

                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            //getting clintname
                                            nameclint = button.getText();
                                            getclintname(nameclint);
                                           

                                            for (clintthread clint : clint_listofclintthreadclass) {
                                                if (clint.name.equals(nameclint)) {
                                                    current_clintnumber = clint.number;
                                                    getclintnumber(current_clintnumber);
                                                    flag = clint.friend;
                                                    System.out.println(flag+" hoise");
                                                }
                                            }

                                            if (clintnamebuttton_clicked == true) {

                                                //refresh start
                                                frame.setVisible(false);
                                                jpanel2.remove(clintnamebuttton);
                                                jpanel2.remove(dplabelclint);

                                                if (flag == true) {
                                                    dpandnamelabel(gridbagconstrain2, flag);
                                                } else {
                                                    dpandnamelabel(gridbagconstrain2, flag);
                                                }

                                                //refresh ends
                                                frame.setVisible(true);
                                            } else {

                                                //refresh start
                                                frame.setVisible(false);
                                                dpandnamelabel(gridbagconstrain2, flag);
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
                            } else if (msg.contains(",_(:);)(")) {

                                StringTokenizer token = new StringTokenizer(msg, ",_(:);)( ", false); //c%@ is doken delimeter
                                clintnumber_string = token.nextToken();

                                clintnumber = Integer.parseInt(clintnumber_string);

                                for (clintthread clint : clint_listofclintthreadclass) {

                                    if (clint.number == clintnumber) {
                                        for (int i = 0;; i++) {
                                            JButton button = (JButton) jpanel301.getComponent(i);
                                            String name = button.getText();
                                            if (clint.name.equals(name)) {
                                                jpanel301.remove(i);
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (clintnumber == current_clintnumber) {
                                    frame.setVisible(false);
                                    jpanel2.remove(clintnamebuttton);
                                    jpanel2.remove(dplabelclint);
                                    frame.setVisible(true);
                                }

                            } else if (msg.contains("&&&&&&&&")) {

                                StringTokenizer token = new StringTokenizer(msg, " &&&&&&&&", false);
                                String friendclintnumber_string = token.nextToken();
                                friendrequestname = token.nextToken();

                                friendrequestnumber = Integer.parseInt(friendclintnumber_string);

                                int decission = JOptionPane.showConfirmDialog(frame, "Do you want to be friends with " + friendrequestname + " ?");
                                if (decission == JOptionPane.YES_OPTION) {
                                    sendingstream.writeUTF(friendrequestnumber + " ^^^^^^^^");
                                }

                            } else if (msg.endsWith("^^^^^^^^")) {

                                StringTokenizer token = new StringTokenizer(msg, " ^^^^^^^^", false);
                                String friendclintnumber_string = token.nextToken();

                                friendrequestnumber = Integer.parseInt(friendclintnumber_string);

                                for (clintthread clint : clint_listofclintthreadclass) {
                                    if (clint.number == friendrequestnumber) {
                                        clint.friend = true;
                                    }
                                }
                                frame.setVisible(false);
                                jpanel2.remove(friendship_statusbutton);
                                frame.setVisible(true);

                            } else {
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
                                for (clintthread clint : clint_listofclintthreadclass) {
                                    if (clint.number == current_clintnumber) {
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
                try {
                    logout = 1;
                    sendingstream.writeUTF("logged out ,_(:);)(");

                } catch (Exception ex) {
                    System.out.println("personalchatroomui sendbutton" + ex);
                }
            }
        });

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
