   
package ui;
import User.userstructure; 
import filedata.accountdata; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class createaccountWindow extends JFrame   {
    private JPanel jpanel1;
    private JLabel usernamelabel,nicknamelabel,passwordlabel ,emaillabel;
     JTextField usernameField;
     JTextField nickField; 
     JPasswordField passwordField;
     JTextField emailfield;
    private JButton submitButton ;
    private accountdata aaccounts; 
    private dpchooser dp ;
     
    //private JFileChooser imagechooser;
    @SuppressWarnings("unused")
    
    
 
    public createaccountWindow( ) {
       
        framekeycreatewindow();
        setVisible(true);
        setDefaultCloseOperation(JFrame. DISPOSE_ON_CLOSE);
        setBounds( 500,80,1250,850);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage( "C:\\Users\\ASUS\\Downloads\\iconlogo.png"); 
         setIconImage(icon); 
      
    }
    
     public void framekeycreatewindow() {
          
         jpanel1=new JPanel();
         jpanel1.setBorder (BorderFactory.createCompoundBorder(
                    new CustomeBorder0() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
         setResizable(false);
         //design
         /*Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\signupbackground.png");
         this.setContentPane(new JPanel() {
         @Override
         public void paintComponent(Graphics g) { 
            super.paintComponent(g);
            g.drawImage(img,0,0, null);
         }
           });*/ 
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Downloads\\signupbackground.png");
        Image image = img.getScaledInstance( 1250,850 ,Image.SCALE_SMOOTH); 
        this.setContentPane(new JLabel(new ImageIcon ( image)));
         
         Font fieldfont = new Font("Arial", Font.PLAIN, 20); 
         
         //Border thickBorder = new LineBorder(Color.white, 5);
         
        //create components
         usernameField = new JTextField();
         usernameField.setFont(fieldfont);
         usernameField.setBackground(Color.white);
         usernameField.setForeground(Color.black);
         usernameField.setColumns(15);
         usernameField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
         
         
        nickField = new JTextField();
        nickField.setFont(fieldfont);
        nickField.setBackground(Color.white);
        nickField.setForeground(Color.green);
        nickField.setColumns(15);
        nickField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
        
        
        passwordField = new JPasswordField();
        passwordField.setFont(fieldfont);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.black);
        passwordField.setColumns(15);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    ))); 
        
        
        emailfield = new JTextField(); 
        emailfield.setBackground(Color.white);
        emailfield.setForeground(Color.black);
        //picturefield.setColumns(15);
        emailfield.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
        
        
        submitButton = new JButton("Signup");
        submitButton.setFont(new Font(Font.SERIF, Font.BOLD,20));
        submitButton.setBackground( Color.WHITE);
        submitButton.setForeground(Color.BLACK); 
        submitButton.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
        /*
        imagebutton = new JButton(" Select");
        imagebutton.setFont(new Font(Font.SERIF, Font.BOLD,20));
        imagebutton.setBackground( Color.WHITE);
        imagebutton.setForeground(Color.BLACK); 
        imagebutton.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder() ,new EmptyBorder(new Insets(10,10,10,10)
                    )));
                                 
        imagechooser =new JFileChooser();*/
        
                                 //layout of jrrame
       this.setLayout(new GridBagLayout());
       GridBagConstraints gridbagconstrain1 = new GridBagConstraints();
        gridbagconstrain1.weightx=1;
       gridbagconstrain1.weighty=1;
       gridbagconstrain1.insets = new Insets(100,0,0,0);
       this.add( jpanel1,gridbagconstrain1 );
        
                                  //layout of jpanel1 
        jpanel1.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain = new GridBagConstraints();
        jpanel1.setBackground(new Color(3,51,20));
        
        
        gridbagconstrain.gridy = 0;
        gridbagconstrain.gridwidth =1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(30, 10, 10, 10);
        usernamelabel = new JLabel();
        usernamelabel.setFont(new Font(Font.SERIF, Font.BOLD,20));
        usernamelabel.setText("Username");
        usernamelabel.setForeground(Color.WHITE);
        //usernamelabel.setFont(font);
        jpanel1.add(usernamelabel,gridbagconstrain );
        
        gridbagconstrain.gridy = 0;
        gridbagconstrain.gridwidth =3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(30, 10, 10, 10);
        jpanel1.add(usernameField,gridbagconstrain );
        
        gridbagconstrain.gridy = 1;
        gridbagconstrain.gridwidth =1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
         nicknamelabel = new JLabel();
         nicknamelabel.setFont(new Font(Font.SERIF, Font.BOLD,20));
         nicknamelabel.setText("Nickname");
         nicknamelabel.setForeground(Color.WHITE);
        //usernamelabel.setFont(font);
        jpanel1.add( nicknamelabel,gridbagconstrain );
 
        gridbagconstrain.gridy = 1; 
        gridbagconstrain.gridwidth =3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 10, 10);
        jpanel1.add( nickField,gridbagconstrain );
        
        gridbagconstrain.gridy = 2;
        gridbagconstrain.gridwidth =1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
          passwordlabel = new JLabel();
          passwordlabel.setFont(new Font(Font.SERIF, Font.BOLD,20));
          passwordlabel.setText("Password");
          passwordlabel.setForeground(Color.WHITE);
        //usernamelabel.setFont(font);
        jpanel1.add(passwordlabel,gridbagconstrain );
        
        gridbagconstrain.gridy = 2; 
        gridbagconstrain.gridwidth =3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 10, 10);
        jpanel1.add( passwordField,gridbagconstrain );
        
        gridbagconstrain.gridy = 3;
        gridbagconstrain.gridwidth =1; 
        //gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
           emaillabel = new JLabel();
           emaillabel.setFont(new Font(Font.SERIF, Font.BOLD,20));
           emaillabel.setText(" Email");
           emaillabel.setForeground(Color.WHITE);
        jpanel1.add( emaillabel,gridbagconstrain );
        
         
        
        gridbagconstrain.gridy = 3; 
        gridbagconstrain.gridwidth =3; 
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 0, 10);
        jpanel1.add(emailfield,gridbagconstrain );
         
         
        gridbagconstrain.gridy = 4; 
        gridbagconstrain.gridwidth =4;
        gridbagconstrain.ipadx=100;
        gridbagconstrain.ipady=0;
        gridbagconstrain.fill = GridBagConstraints.CENTER;
        gridbagconstrain.insets = new Insets(5,100,30, 10);
        jpanel1.add(submitButton,gridbagconstrain );
        
          
        
       submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if (usernameField.getText().equalsIgnoreCase("")||nickField.getText().equalsIgnoreCase("")||passwordField.getText().equalsIgnoreCase("")||  emailfield.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(jpanel1,"Fill all the information");
                } 
               
                else {  
                       //JOptionPane.showMessageDialog(jpanel1 ,"Thanx for signing up"); 
                         // setVisible(false); 
                          
                        aaccounts =new accountdata();
                        userstructure newUser= aaccounts.createAccount(String.valueOf(passwordField.getPassword()), usernameField.getText(),
                        nickField.getText(), emailfield.getText() ); 
                        String  name=usernameField.getText() ;
                        String filename=name+".txt";
                        dp= new dpchooser(newUser,filename,name) ;  //this dpchooser class select dp and also creates a final users object file
                       
                          
                }
            }
        });
    
      /*  imagebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
             imagechooser.setFileFilter(new FileNameExtensionFilter("Open Image","jpg","png","jpeg"));
             int returnVal =imagechooser .showOpenDialog(rootPane);
             if(returnVal==JFileChooser .APPROVE_OPTION){
                 File file=imagechooser.getSelectedFile();
                 Image  displaypicture =  Toolkit.getDefaultToolkit().getImage( file.getAbsolutePath()); 
                 Image  displaypicturescaled= displaypicture.getScaledInstance(150,85,Image.SCALE_SMOOTH); 
                 ImageIcon  dp= new ImageIcon( displaypicturescaled);
                  
                 picturetextpane.insertIcon( dp);
             }
            }
        });*/
     
        
     }

   

 
 
//design of the borders 
    private static class CustomeBorder0  extends AbstractBorder{
     public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        } 
    }
       private static class CustomeBorder extends AbstractBorder{
     public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(20));
            g2d.setColor( new Color(1,51,20));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 30, 30);
    
}}
        
}
  
