 
package clint; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.StringTokenizer;

public class clintthread extends clintsocket implements Runnable{
 
     public ArrayList<String> msgelist;
     public String name;
     public int number;
     public boolean friend;
     
    
    public clintthread (String name,int number,boolean friend ) {
        this.name=name;
        this.number=number;
        this.friend=friend; 
        msgelist=new ArrayList<String>();
    }
    
    
    @Override
    public void run() { 
         
        //code if needes
    
    }
    
  
}

