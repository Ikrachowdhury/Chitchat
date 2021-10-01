package ui;

import clint.file_name_transfer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectFileCreate {

    Object object;
    String filename, name;
    InetAddress inetAddress;

    public ObjectFileCreate(Object object, String filename, String name) {
        try {
            this.filename = filename;
            this.object = object;
            this.name = name;
            this.inetAddress = InetAddress.getLocalHost();

            createfile();
            //file_name_transfer file = new file_name_transfer(filename, name);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    public String createfile() {

        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException ex) {
           
        }
        if (file.exists()) {
            try {
                ObjectOutputStream objectwrite = new ObjectOutputStream(new FileOutputStream(file));
                objectwrite.writeObject(object);
                objectwrite.writeObject(inetAddress);
                objectwrite.close();

            } catch (IOException ei) {
                ei.printStackTrace();
            }

        }
        return filename;
    }
}
