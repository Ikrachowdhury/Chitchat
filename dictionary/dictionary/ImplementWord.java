
package dictionary;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ImplementWord extends JFrame{
    
    private static final long serial=5L;
    
    private JTextField tfWord;
    private JTextArea taMeaning;
    private JButton addBtn;
    private JFrame frm;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    
    public ImplementWord() {
      /* frm=new JFrame();      
       frm.setTitle("Add Word"); */
        
        super("Add Word");
       
       gbl = new GridBagLayout();
       gbc = new GridBagConstraints();
       gbc.insets=new Insets(5,5,10,5);
       gbc.fill=GridBagConstraints.BOTH;
       
       tfWord= new JTextField(25);
       taMeaning= new JTextArea();
       addBtn = new JButton("Add Button");
       addBtn.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent ae) {
               if(tfWord.getText().length() >0 && taMeaning.getText().length() >0){
                   Dictionary.addWord(tfWord.getText(),taMeaning.getText());
                   JOptionPane.showMessageDialog(ImplementWord.this,"Added Word Successfully!","Add Word",JOptionPane.INFORMATION_MESSAGE);
                   tfWord.setText(" ");
                   taMeaning.setText(" ");
                   tfWord.requestFocus();
               }
               else{
                   JOptionPane.showMessageDialog(ImplementWord.this,"Please enter Word and meaning!","Add Word",JOptionPane.INFORMATION_MESSAGE);
               }
           }
                   
       });
       
       Container c= getContentPane();
       c.setLayout(gbl);
       
       // for Add Word
       gbc.anchor=GridBagConstraints.EAST;
       c.add(new JLabel("Enter Word : "),gbc);
       gbc.anchor=GridBagConstraints.WEST;
       c.add(tfWord);
       
       //for add Meaning
       gbc.gridx=0;
       gbc.anchor=GridBagConstraints.EAST;
       c.add(new JLabel("Add Meaning : "),gbc);
       gbc.anchor=GridBagConstraints.WEST;
       gbc.gridx=1;
       gbc.gridwidth=2;
       gbc.gridheight=2;
       taMeaning.setRows(3);
       taMeaning.setColumns(30);
       JScrollPane sp=new JScrollPane(taMeaning,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       c.add(sp,gbc);
       
       //for add Button
       gbc.gridx=0;
       gbc.gridwidth=3;
       gbc.anchor=GridBagConstraints.CENTER;
       gbc.fill=GridBagConstraints.NONE;
       c.add(addBtn,gbc);
       
       pack();
       
       //For checking only from next
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       setVisible(true);
    }
    /*
    public static void main(String[] args) {
        ImplementWord a=new ImplementWord();
        
    }
    */           
    
}
