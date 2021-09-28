
package tictactoy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TictacMainFrame {
JFrame fr;
JPanel p2,p1;
    public TictacMainFrame() {
        fr= new JFrame("Tic Tac Toy");
        fr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fr.setBounds(500,100,500,300);
        fr.setResizable(false);
        
        p1= new JPanel();
        JLabel label= new JLabel("<html><h1 color=red> Instructions :</h1><br/>"
                + "                          1. X will play first and then O's turn.<br/>"
                + "                          2. After getting XXX or OOO from any straight line,the game will over.<br/>"
                + "                          3. X will win the game if X get XXX first else O win.<br/>"
                + "                          4. When anyone can't get XXX or OOO but all blocks are played<br/>"
                + "                             then the game will Draw and over.<br/><br/><br/>");
        p1.add(label);
        fr.add(p1,BorderLayout.NORTH);
        
        //Icon icon=new ImageIcon("start.jpeg");
        //JButton b= new JButton());
        
        p2=new JPanel();
        JButton b= new JButton(getImage("next.jpeg"));
        p2.add(b);
        fr.add(p2,BorderLayout.SOUTH);
    
    b.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent a) {
            
           addButton(); /* new TicTacToy(); */
         
        }
    });
    fr.setVisible(true);
    }
    
    public void addButton(){
       TicTacToy tic= new TicTacToy();
       //tic.setVisible(true);
    }
    
    public ImageIcon getImage(String filename){
        return new ImageIcon(this.getClass().getResource(filename));
    }
    
    public static void main(String[] args) {
        TictacMainFrame m= new TictacMainFrame();
        
        
    }
}
