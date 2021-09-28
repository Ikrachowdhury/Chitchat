
package dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dictionary {
    
    private static boolean modified = false;
    private static String dictionaryfile;
    private static String message="";
    
    private static TreeMap<String,String> words= new TreeMap<String,String>();
    
    static {
        dictionaryfile=System.getProperty("user.dir") + 
            "/dictionary.ser";
    }
    
    public static boolean isModified(){
        return modified;
    }
    
    public String getMessage(){
        return message;
    }
    
    public static void setMessage(String message){
        Dictionary.message=message;
    }
    
    public static TreeMap<String,String>getWords(){
        return words;
    }
    
    public static String searchWord(String word){
        return words.get(word);
    }
    
    public static void addWord(String Word,String meaning){
        words.put(Word, meaning);
        modified = true;
    }
    
    public static boolean deleteWord(String word){
                Object done = words.remove(word);
                if(done==null)
                    return false;
                else
                    return true;
    }
    
    public static boolean saveToDisk(){
        try {
            FileOutputStream fs= null;
            fs = new FileOutputStream(dictionaryfile);
             ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(words);
            os.close();
            fs.close();
            modified = false;
            return true;
        } catch (Exception ex) {
             message=ex.getMessage();
            return false;
        }
           
        }
    
    public static boolean loadFromDisc(){
        try {
            FileInputStream fs = new FileInputStream(dictionaryfile);
            ObjectInputStream is = new ObjectInputStream(fs);
            words = (TreeMap<String,String>) is.readObject();
            is.close();
            fs.close();
            modified = false;
            return true;
        } catch (Exception ex) {
            message=ex.getMessage();
            return false;
        }
        
    }
    }
            
            

