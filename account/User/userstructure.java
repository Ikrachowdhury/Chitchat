 
package User;
 
import java.io.Serializable;

public class userstructure implements Serializable {
   private String pwd;
    private String username;
    private String nick;
    private String email;
    private boolean isLoggedIn;
     

    public  userstructure(String pwd, String username, String nick, String email) {
        this.pwd = pwd;
        this.username = username;
        this.nick = nick;
        this.email = email;

        this.isLoggedIn = false;
    }

    public userstructure(String[] info) {
        pwd = info[1];
        username = info[2];
        nick = info[3];
        email = info[4];
    }

    public userstructure() {

    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public boolean setIsLoggedIn(boolean status) {
        this.isLoggedIn = status;
        return true;
    }

    public void setAll(String[] tmp) {
        pwd = tmp[1];
        username = tmp[2];
        nick = tmp[3];
        email = tmp[4];
    }

    public String getNameOrNick() {
        return nick != null && !nick.isEmpty() ? nick : username;
    }
    public String toString() {
        return username+" "+nick+" "+email;
    }
}
