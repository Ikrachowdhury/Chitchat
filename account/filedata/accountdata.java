 
package filedata;
import User.userstructure;
import java.io.*;
import java.util.ArrayList;

public class accountdata {
    boolean exists,available;
    private ArrayList<userstructure> accounts;
    
      public accountdata() { 
        accounts = new ArrayList<>();
        populateAccounts("datafile1.txt");
    }
 

    private void populateAccounts(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String trimmed = line.trim();
                    String[] splitted = trimmed.split(" ");
                    userstructure u = new userstructure(splitted[0], splitted[1], splitted[2], splitted[3]);
                    accounts.add(u);
                }
                fileReader.close();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  

    public    boolean checkCredentials(String username, String password) {
        for (userstructure acct : accounts) {
          
            if (acct.getUsername().equals(username) && acct.getPwd().equals(password)) {
                exists=true  ;
                break;
            }
        }
        return  exists;
    }
    
    public    boolean checkAvaiability(String username) {
        available=true;
        for (userstructure acct : accounts) {
             
            if (acct.getUsername().equals(username)) {
                available=false;
            }else{
               available=true; 
            }
        }
        return available;
    }

    /**
     * Appends new accounts to file
     */
    private void addNewAccount(String pwd, String name, String nick, String email) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datafile1.txt", true));
            bw.write(pwd + " " + name + " " + nick + " " + email);
            bw.newLine();
           
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Looks through the accounts database to find an account that matches the given information
     *
     * @param username the username of the account to find
     * @return true    if the username isn't in the database
     * false if the username is in the database
     */
    public boolean userIsAvailable(String username) {
        for (userstructure acct : accounts) {
            if (acct.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }
    
   

    /**
     * Creates an account and adds it to the database.
     */
    public userstructure  createAccount(String pwd, String name, String nick, String email) {
         userstructure newAcct = new  userstructure(pwd, name, nick, email);
         accounts.add(newAcct);
         addNewAccount(pwd, name, nick, email);
         return newAcct;
    }

}
