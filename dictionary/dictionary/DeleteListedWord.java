
package dictionary;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteListedWord extends JFrame{
    
    private static final long serial= 5L;
    private JTextField tfWord;
    private JButton delBtn;
   // private JFrame frm;

    public DeleteListedWord() {
        //frm = new JFrame("Delete Word");
        
        super("Delete Word");
        tfWord = new JTextField(20);
        delBtn = new JButton("Delete");
        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tfWord.getText().length() >0){
                    boolean done = Dictionary.deleteWord(tfWord.getText());
                    if(!done){
                        JOptionPane.showMessageDialog(DeleteListedWord.this, "Word not found! Please try again.","Delete Word",JOptionPane.INFORMATION_MESSAGE);
                    }
                        else {
                        JOptionPane.showMessageDialog(DeleteListedWord.this, "Word deleted successfully!","Delete Word",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(DeleteListedWord.this,"Please Enter word from dictionary!","Add Word",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JLabel("Word to Delete : "));
        c.add(tfWord);
        c.add(delBtn);
        pack();
        
        //For checking only from next
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       setVisible(true);
    }
    /*
    public static void main(String[] args) {
        DeleteListedWord a = new DeleteListedWord();
    }
    */
    
}
