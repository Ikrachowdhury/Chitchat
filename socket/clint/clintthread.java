 
package clint; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.StringTokenizer;

public class clintthread extends clintsocket implements Runnable{
 
     public ArrayList<String> msgelist;
     public String name;
     public int number;
     
    
    public clintthread (String name,int number) {
        this.name=name;
        this.number=number;
        msgelist=new ArrayList<String>();
    }
    
    
    @Override
    public void run() { 
         
        //code if needes
    
    }
    
  
}

