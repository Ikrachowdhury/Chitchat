
package dictionary;

import java.awt.*;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.*;

public class DisplayWord extends JFrame{
    private static final long serial = 5L;

    public DisplayWord() {
        //JFrame frm = new JFrame("Word Display");
        super("Word Display");
        Container c= getContentPane();
        Vector < String > headings = new Vector < String >();
        headings.add("English");
        headings.add("Bangla");
        
        Vector < Vector < String > > rows = new Vector < Vector < String > >();
        
        TreeMap< String, String> words = Dictionary.getWords();
        for(String word : words.keySet()){
            Vector <String> row = new Vector<String>();
            row.add(word);
            row.add(words.get(word));
            rows.add(row);
        }
        
        JTable table = new JTable(rows,headings);
        
        JScrollPane s = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        c.add(s);
        
        pack();
        
        //For checking only
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //setSize(500,400);
        setResizable(false);
        setBounds(300,50,500,400);
    }
    /*
    public static void main(String[] args) {
        DisplayWord a= new DisplayWord();
    }
    */
}
