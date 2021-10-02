package filedata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class chathistory {

    String chats;
    boolean availability;

    public void createHistoryFile(String filename) {
        try {
            File chat_history = new File(filename);
            if (!chat_history.exists()) {
                chat_history.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getting_chatHIdtory(String filename) {
        try {
            File chat_history = new File(filename);
            if (chat_history.exists()) {
                FileReader fileReader = new FileReader(chat_history);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                chats = "";
                while ((line = bufferedReader.readLine()) != null) {
                    if (chats.equals("")) {
                        chats = line;
                    } else {
                        chats += "\n" + line;
                    }

                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    public boolean chatfileavailability(String filename) {
        try {
            File chat_history = new File(filename);
            if (chat_history.exists()) {
                availability = true;
            } else {
                availability = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availability;
    }

    public void WriteHistory(String filename, String msg) {
        try {
            File chat_history = new File(filename);
            if (chat_history.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
                bw.write(msg);
                bw.newLine();

                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
