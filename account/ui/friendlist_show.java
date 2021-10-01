 package ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class friendlist_show extends JFrame implements ActionListener {
    String path;
    File folder;
    File[] listOfFiles;
    String[] fileNames;
    JMenuBar menuBar;
    JMenu all_files, photos, docs;
    JMenuItem all_file_list, photos_list, doc_list;

    friendlist_show(String path) {
        this.path = path;
        folder = new File(path);
        listOfFiles = folder.listFiles();
        fileNames = new String[listOfFiles.length];

    }

    public void display() {
        final JFrame frame = new JFrame("File Display");
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel p = new JPanel();
        p.setBounds(50, 50, 500, 500);
        p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        p.add(createMenuBar());
        frame.add(p);
        frame.setVisible(true);
    }
    //Create MENU  

    JMenuBar createMenuBar() {
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                fileNames[i] = listOfFiles[i].getName(); //store the file names in an array

            }
        }

        menuBar = new JMenuBar();
        photos = new JMenu("                                     Photos");
        photos.setPreferredSize(new Dimension(250, 100));
        photos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        docs = new JMenu("                       Documents");
        docs.setPreferredSize(new Dimension(250, 100));
        docs.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        for (final String list : fileNames) {

            if (list.endsWith(".txt") || list.endsWith(".TXT") || list.endsWith(".docx")) {
                doc_list = new JMenuItem(list);
                doc_list.setPreferredSize(new Dimension(250, 70));
                doc_list.addActionListener(this);

                docs.add(doc_list);
            } else if (list.endsWith(".png") || list.endsWith(".jpg")) {
                photos_list = new JMenuItem(list);
                photos_list.setPreferredSize(new Dimension(250, 70));
                photos_list.addActionListener(this);

                photos.add(photos_list);
            }
        }

        menuBar.add(photos);
        menuBar.add(docs);
        menuBar.setBackground(Color.LIGHT_GRAY);

        return menuBar;
    }

    //Open the clicked file
    public void actionPerformed(ActionEvent ae) {
        try {
            File file = new File(path + "//" + ae.getActionCommand());
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }

        } catch (IOException ex) {
            Logger.getLogger(FileShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
