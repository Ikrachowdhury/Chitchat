 
package filedata;
 
import User.userstructure; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class objectread {
   public String filename,username,nickname,email;
   userstructure user;
   public ImageIcon dp;
   InetAddress inetAddress;  
  
    
  public objectread(String filename)  {
      this.filename=filename;
      readobjectuser();
      readobjectdp();
      readobjectip();
      userallobjectpass(); 
    }
  
   public userstructure readobjectuser(){
       try{
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream objectread = new ObjectInputStream(file);
            user =(userstructure) objectread.readObject();  
            objectread.close(); 
       }
       catch( Exception e){
           System.out.println(e); }
       
            return user;
            
   }
   public  ImageIcon readobjectdp() {
        try{   
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream objectread = new ObjectInputStream(file); 
            user =(userstructure) objectread.readObject();
            dp=(ImageIcon) objectread.readObject(); 
            inetAddress=(InetAddress) objectread.readObject(); 
            objectread.close(); 
        } 
        
        catch( Exception e){
           System.out.println(e); }
         
            return dp;
            
   }
   public   InetAddress readobjectip() {
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream objectread = new ObjectInputStream(file); 
            user =(userstructure) objectread.readObject();
            dp=(ImageIcon) objectread.readObject();
            inetAddress=(InetAddress) objectread.readObject();
            objectread.close(); }
            catch( Exception e){
           System.out.println(e);}
             
            return inetAddress ;
            
   }
   public  String userallobject( String username, String nickname, String email) {
      
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        return username+nickname+email;
 
    } 
   public void userallobjectpass(){
        userallobject(user.getUsername(),user.getNick(),user.getemail()); 
   }
}
