 
package ui; 
import clint. file_name_transfer;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Font;
import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class dpchooser  implements  Serializable  {

    static void setFileFilter(FileNameExtensionFilter fileNameExtensionFilter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    private JFileChooser  dpchooser;
    private JButton imagebutton,submitbutton;
    private JTextPane imageshowarea;
    private Object object; 
    private  Image  displaypicture;
    private ImageIcon  dp; 
    private InetAddress inetAddress;
    String filename,name;
    JFrame imagechooserframe=new JFrame();
    
    //this class chose dp and creates object of totall clint information
    public dpchooser(Object object,String filename,String name){
        this.filename=filename;
        this.object=object;
        this.name=name;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(dpchooser.class.getName()).log(Level.SEVERE, null, ex);
        }
        framekey();
        imagechooserframe.setVisible(true);
        imagechooserframe.setDefaultCloseOperation(JFrame. DISPOSE_ON_CLOSE);
        imagechooserframe.setBounds( 500,80,500,500);
        imagechooserframe.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage( "C:\\Users\\ASUS\\Downloads\\iconlogo.png"); 
        imagechooserframe.setIconImage(icon); 
        
        
    }
    public void framekey(){ 
        imagechooserframe.setBackground(Color.BLACK);
        imageshowarea=new JTextPane(); 
        dpchooser =new JFileChooser();
       
        imagebutton=new JButton("To select image click here");
        imagebutton.setFont(new Font(Font.SERIF, Font.BOLD,20));
        imagebutton.setForeground(Color.white);
        imagebutton.setBackground(new Color(1,51,20));
        
        submitbutton=new JButton("submit"); 
        submitbutton.setFont(new Font(Font.SERIF, Font.BOLD,20));
        submitbutton.setForeground(Color.white);
        submitbutton.setBackground(new Color(1,51,20));
         
        imagechooserframe.add( imageshowarea,BorderLayout.CENTER);
        imagechooserframe.add(imagebutton,BorderLayout .PAGE_START);
        imagechooserframe.add(submitbutton,BorderLayout.PAGE_END);
         
        //userinfo= new userallinfo(object);  
        
        
        imagebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageshowarea.setText(null);
                dpchoosermethod();
            }
        });
           
         submitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                imagechooserframe.setVisible(false);
                 
                createfile();
                file_name_transfer file =new  file_name_transfer(filename,name);
                
                //loginwindow login= new loginwindow();
                
}});
                 
                 }
           
        
    
      public ImageIcon dpchoosermethod(){
          
            dpchooser.setFileFilter(new FileNameExtensionFilter("Open Image","jpg","png","jpeg"));
             int returnVal =dpchooser .showOpenDialog(imageshowarea);
             
             if(returnVal==JFileChooser.APPROVE_OPTION){
                 File file=dpchooser.getSelectedFile();
                  displaypicture =  Toolkit.getDefaultToolkit().getImage( file.getAbsolutePath()); 
                 Image  displaypicturescaled= displaypicture.getScaledInstance( 500,500,Image.SCALE_SMOOTH); 
                 ImageIcon  dp1= new ImageIcon( displaypicturescaled);
                 imageshowarea.insertIcon( dp1);
                 Image  displaypicturescaled2= displaypicture.getScaledInstance( 100,100,Image.SCALE_SMOOTH); 
                 ImageIcon dp= new ImageIcon( displaypicturescaled2);
                 this.dp=dp;
          
          }
             return dp;
            }
      
      
       
       public  String createfile(){ 
           
         File file = new File(filename);
        try {
            file .createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(dpchooser.class.getName()).log(Level.SEVERE, null, ex);
        }
                if(file.exists()){
                    try { 
                        ObjectOutputStream objectwrite=new ObjectOutputStream(new FileOutputStream(file));
                        objectwrite.writeObject(object); 
                        objectwrite.writeObject(inetAddress);
                        objectwrite.close();
                        
                    } catch (IOException ei ) {
                        ei.printStackTrace();
                    }
                    
                }
                return filename;
       }
      
}
