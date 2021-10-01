package ui;

import User.userstructure;
import filedata.accountdata;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

public class createaccountWindow extends JFrame {

    private JPanel jpanel1,jpanel2;
    private JLabel usernamelabel, nicknamelabel, passwordlabel, emaillabel,logolabel,welcomelabel1,informationlabel;
    JTextField usernameField;
    JTextField nickField;
    JPasswordField passwordField;
    JTextField emailfield;
    private JButton submitButton;
    private accountdata aaccounts;
    private ObjectFileCreate objectCreate;
    public loginwindow loginPage;

    //private JFileChooser imagechooser;
    @SuppressWarnings("unused")

    public createaccountWindow() {
        aaccounts = new accountdata();
        framekeycreatewindow();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 80, 1250, 850);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/iconlogo.png"));
        setIconImage(icon);
   

    }

    public void framekeycreatewindow() {
 
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
 
        //design
 
        Font fieldfont = new Font("Arial", Font.BOLD, 20);
 
        //create components
        //components of jpanel2
        Image backgroundlogo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ui/signUPlogo.png"));
        Image logoscaled = backgroundlogo.getScaledInstance(500, 500, Image.SCALE_SMOOTH); //image resizer jonno
        ImageIcon logo = new ImageIcon(logoscaled);
        
        logolabel=new JLabel();
        logolabel.setIcon(logo);
        
        //components of jpanel1
        
        informationlabel=new JLabel();
        informationlabel.setText("Fill up the Form");
        informationlabel.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        informationlabel.setForeground(Color.WHITE);
        
        
        usernameField = new JTextField();
        usernameField.setFont(fieldfont);
        usernameField.setBackground(Color.white);
        usernameField.setForeground(Color.black);
        usernameField.setColumns(15);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        nickField = new JTextField();
        nickField.setFont(fieldfont);
        nickField.setBackground(Color.white);
        nickField.setForeground(Color.BLACK);
        nickField.setColumns(15);
        nickField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        passwordField = new JPasswordField();
        passwordField.setFont(fieldfont);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.black);
        passwordField.setColumns(15);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        emailfield = new JTextField();
        emailfield.setFont(fieldfont);
        emailfield.setBackground(Color.white);
        emailfield.setForeground(Color.black);
        emailfield.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        submitButton = new JButton("Signup");
        submitButton.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        submitButton.setBackground(Color.WHITE);
        submitButton.setForeground(Color.BLACK);
        submitButton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), new EmptyBorder(new Insets(10, 10, 10, 10)
                )));

        //layout of jrrame
 
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain2 = new GridBagConstraints();
        gridbagconstrain2.weightx=1;
        gridbagconstrain2.weighty=1;
        
         gridbagconstrain2.ipadx=1000;
         gridbagconstrain2.ipady=1000;
          

        gridbagconstrain2.gridx = 0;
        gridbagconstrain2.gridwidth = 1;
        this.add(jpanel2, gridbagconstrain2);

        gridbagconstrain2.gridx = 1;
        gridbagconstrain2.gridwidth = 1;
       // gridbagconstrain2.insets = new Insets(0,0, 0, 0);
        this.add(jpanel1, gridbagconstrain2);

        //layout of jpanel1 
        jpanel1.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain = new GridBagConstraints();
        jpanel1.setBackground(new Color(28, 73, 102));

        gridbagconstrain.ipadx = 100;
        gridbagconstrain.ipady = 15;
        
        gridbagconstrain.gridy = 0;
        gridbagconstrain.gridwidth =3;
        gridbagconstrain.fill = GridBagConstraints.CENTER;
        gridbagconstrain.insets = new Insets(30,250, 10, 10);
        jpanel1.add(informationlabel, gridbagconstrain);
 
         
        gridbagconstrain.gridy = 1;
        gridbagconstrain.gridwidth = 1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(30, 10, 10, 10);
        usernamelabel = new JLabel();
        usernamelabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        usernamelabel.setText("Username");
        usernamelabel.setForeground(Color.WHITE);
        jpanel1.add(usernamelabel, gridbagconstrain);

        gridbagconstrain.gridy = 1;
        gridbagconstrain.gridwidth = 3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(30, 10, 10, 10);
        jpanel1.add(usernameField, gridbagconstrain);

        gridbagconstrain.gridy = 2;
        gridbagconstrain.gridwidth = 1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
        nicknamelabel = new JLabel();
        nicknamelabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        nicknamelabel.setText("Nickname");
        nicknamelabel.setForeground(Color.WHITE);
        jpanel1.add(nicknamelabel, gridbagconstrain);

        gridbagconstrain.gridy = 2;
        gridbagconstrain.gridwidth = 3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 10, 10);
        jpanel1.add(nickField, gridbagconstrain);

        gridbagconstrain.gridy = 3;
        gridbagconstrain.gridwidth = 1;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
        passwordlabel = new JLabel();
        passwordlabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        passwordlabel.setText("Password");
        passwordlabel.setForeground(Color.WHITE);
        jpanel1.add(passwordlabel, gridbagconstrain);

        gridbagconstrain.gridy = 3;
        gridbagconstrain.gridwidth = 3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 10, 10);
        jpanel1.add(passwordField, gridbagconstrain);

        gridbagconstrain.gridy = 4;
        gridbagconstrain.gridwidth = 1; 
        gridbagconstrain.insets = new Insets(10, 10, 10, 10);
        emaillabel = new JLabel();
        emaillabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        emaillabel.setText(" Email");
        emaillabel.setForeground(Color.WHITE);
        jpanel1.add(emaillabel, gridbagconstrain);

        gridbagconstrain.gridy = 4;
        gridbagconstrain.gridwidth = 3;
        gridbagconstrain.fill = GridBagConstraints.BOTH;
        gridbagconstrain.insets = new Insets(5, 10, 0, 10);
        jpanel1.add(emailfield, gridbagconstrain);

        gridbagconstrain.gridy = 5;
        gridbagconstrain.gridwidth = 4;
        gridbagconstrain.fill = GridBagConstraints.CENTER;
        gridbagconstrain.insets = new Insets(5, 150, 30, 10);
        jpanel1.add(submitButton, gridbagconstrain);
        
        
        //layout of jpanel2
        jpanel2.setLayout(new GridBagLayout());
        GridBagConstraints gridbagconstrain3 = new GridBagConstraints();
        
        jpanel2.setBackground(new Color(237, 234, 222));
        
        gridbagconstrain3.gridy = 0;
        gridbagconstrain3.gridwidth = 3; 
        gridbagconstrain3.insets = new Insets(0, 0,40, 0);
        jpanel2.add(logolabel, gridbagconstrain3);

        gridbagconstrain3.gridy = 1;
        gridbagconstrain3.gridwidth = 3;
        welcomelabel1 = new JLabel("WElcome to SignUP page");
        welcomelabel1.setForeground(new Color(28, 73, 102));
        welcomelabel1.setFont(new Font(Font.SERIF, Font.BOLD, 60));
        gridbagconstrain3.insets = new Insets(0, 0,20, 0);
        jpanel2.add(welcomelabel1, gridbagconstrain3);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usernameField.getText().equalsIgnoreCase("") || nickField.getText().equalsIgnoreCase("") || passwordField.getText().equalsIgnoreCase("") || emailfield.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(jpanel1, "Fill all the information");
                } else {
                     
                    boolean available = aaccounts.checkAvaiability(usernameField.getText());

                    if (available == false) {
                        JOptionPane.showMessageDialog(jpanel1, "This username is not Available");
                        usernameField.setText("");

                    } else {
                        userstructure newUser = aaccounts.createAccount(String.valueOf(passwordField.getPassword()), usernameField.getText(),
                                nickField.getText(), emailfield.getText());
                        String name = usernameField.getText();
                        String filename = name + ".txt";
                        objectCreate = new ObjectFileCreate(newUser, filename, name);
                        File f = new File("E:\\"+usernameField.getText()+" "+nickField.getText());
                        f.mkdir();

                        loginPage = new loginwindow();
                       

                    }
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
            g2d.setStroke(new BasicStroke(20));
            g2d.setColor(new Color(28, 73, 102));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 30, 30);

        }
    }

}
