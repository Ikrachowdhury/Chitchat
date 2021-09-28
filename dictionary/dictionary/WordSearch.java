
package dictionary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class WordSearch extends JFrame{
    private static final long serial= 5L;
    private JTextField tfword;
    private JTextArea meaning;
    private JButton btn;

    public WordSearch() {
       // JFrame frm = new JFrame("Search Word");
        super("Search Word");
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,10,5);
        gbc.fill = GridBagConstraints.BOTH;
        
        tfword = new JTextField(20);
        meaning = new JTextArea();
        btn = new JButton("Search");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tfword.getText().length() >0){
                    String mean = Dictionary.searchWord(tfword.getText());
                    if(mean != null){ 
                        meaning.setText(mean);
                    }
                    else {
                        JOptionPane.showMessageDialog(WordSearch.this, "Word not found!", "Search Word", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(WordSearch.this, "Please Enter Word from Dictionary", "Search Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
       Container c = getContentPane();
       c.setLayout(gbl);
       
       //Add Word
       gbc.anchor = GridBagConstraints.EAST;
       c.add(new JLabel("Search Word : "),gbc);
       gbc.anchor = GridBagConstraints.WEST;
       c.add(tfword);
       gbc.anchor = GridBagConstraints.EAST;
       c.add(btn);
       
       //Add Meaning
       gbc.gridx = 0;
       gbc.anchor = GridBagConstraints.EAST;
       c.add(new JLabel("Meaning : "),gbc);
       gbc.anchor = GridBagConstraints.WEST;
       
       gbc.gridx = 1;
       gbc.gridwidth = 2;
       gbc.gridheight = 2;
       
        meaning.setRows(3);
        meaning.setColumns(30);
        
        JScrollPane p = new JScrollPane(meaning,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.add(p,gbc);
        pack();
       
        //For Test only
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(c);
        setSize(500,200);
    }
    /*
    public static void main(String[] args) {
        WordSearch a= new WordSearch();
    }
    */
    
}
