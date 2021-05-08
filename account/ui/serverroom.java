package ui;
 
import javax.swing.*;
import java.awt.*; 
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicMenuBarUI;
 
public class serverroom   {

    private JPanel jpanel1, jpanel2, jpanel301, jpanel201;
    private JLabel dplabel, connectedfriendslabel, namelabel;
    private JButton sendbutton, clintname_button;
    private JScrollPane allmsgscrollpane, usermsgscrollpane, connectedfriendsscrollpane;
    private JMenuBar registered_clint;
    private JMenu registeredpeoplemenu;
    private JMenuItem file, picture;
    private JTextPane allmsgtextpane, usermsgtextpane;
    public ImageIcon dp, dpclint;
    public String nameclint,msg2;

    //creating frame and icon
    JFrame frame = new JFrame();

    Image logobackground = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\backgroundlogo.jpg");
    Image logobackgroundscaled = logobackground.getScaledInstance(200, 100, Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon logo = new ImageIcon(logobackgroundscaled);

    //creating constructor
    public serverroom() {
//        //super();
//        try {
//            sserversocket = new ServerSocket(666);
//        } catch (IOException ex) {
//            Logger.getLogger(serverroom.class.getName()).log(Level.SEVERE, null, ex);
//        }
        framekeyserverroom();

        //setting frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(1000, 50, 1000, 950);
        frame.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\iconlogo.png");
        frame.setIconImage(icon);
        frame.pack();
    }

    public void framekeyserverroom() {

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
        jpanel201.setLayout(new BoxLayout(jpanel201, BoxLayout.PAGE_AXIS));

        //create components 
        //jpanel1 component      
        /* Image displaypicture= Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\backgroundlogo.jpg");
    Image  displaypicturescaled= displaypicture.getScaledInstance(100,100,Image.SCALE_SMOOTH); //image resizer jonno
    ImageIcon  dp= new ImageIcon( displaypicturescaled);*/
        dplabel = new JLabel(logo);
        dplabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        namelabel = new JLabel("SERVER");
        namelabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        namelabel.setForeground(Color.white);
        namelabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        registered_clint = new JMenuBar();
        registered_clint.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(new Color(1, 51, 20));
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        registeredpeoplemenu = new JMenu("Registered people");
        registeredpeoplemenu.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        registeredpeoplemenu.setForeground(Color.WHITE);
        registeredpeoplemenu.setBackground(Color.BLACK);
        registered_clint.add(registeredpeoplemenu);

        file = new JMenuItem("Files");
        file.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        file.setForeground(Color.white);
        file.setBackground(Color.BLACK);
        registeredpeoplemenu.add(file);

        picture = new JMenuItem("Pictures");
        picture.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        picture.setForeground(Color.white);
        picture.setBackground(Color.BLACK);
        registeredpeoplemenu.add(picture);

        connectedfriendslabel = new JLabel("Connected People");
        connectedfriendslabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        connectedfriendslabel.setForeground(Color.white);
        connectedfriendslabel.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder1(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        connectedfriendsscrollpane = new JScrollPane(jpanel201);
        connectedfriendsscrollpane.setPreferredSize(new Dimension(200, 300));

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

        jpanel1.add(dplabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 1;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 20;
        gridbagconstrain1.gridwidth = 1;
        //gridbagconstrain1.fill = GridBagConstraints .VERTICAL;
        gridbagconstrain1.insets = new Insets(0, 0, 50, 0);

        jpanel1.add(namelabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 2;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(registered_clint, gridbagconstrain1);

        gridbagconstrain1.gridy = 3;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

        jpanel1.add(connectedfriendslabel, gridbagconstrain1);

        gridbagconstrain1.gridy = 4;
        gridbagconstrain1.gridx = 1;
        gridbagconstrain1.ipady = 0;
        gridbagconstrain1.gridwidth = 1;
        gridbagconstrain1.fill = GridBagConstraints.CENTER;
        gridbagconstrain1.insets = new Insets(0, 0, 20, 0);

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

        jpanel2.add(sendbutton, gridbagconstrain2);

        //layout of main jframe
        /*
       frame.setLayout(new GridBagLayout());
       GridBagConstraints gridbagconstrain = new GridBagConstraints();
       gridbagconstrain.weightx=1;
       gridbagconstrain.weighty=1;
        
       gridbagconstrain.gridx=0; 
       gridbagconstrain.gridwidth =1; 
       
       gridbagconstrain.insets = new Insets(0,0,50,20);
       frame.add( jpanel1,gridbagconstrain );
        
       gridbagconstrain.gridx=1; 
       gridbagconstrain.gridwidth =1; 
       gridbagconstrain.insets = new Insets(0,0,0,40);
       frame.add( jpanel2,gridbagconstrain ); 
         */
        //frame.add(jpanel3,BorderLayout.EAST);  
        frame.add(jpanel1, BorderLayout.WEST);
        frame.add(jpanel2, BorderLayout.CENTER);
 

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

}
