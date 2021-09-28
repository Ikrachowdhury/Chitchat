
package tictactoy;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToy extends JPanel{
    
    
    static String[] block;
    static String  turn;
    static ArrayList<JButton> button = new ArrayList<JButton>( );
    static JFrame frame= new JFrame("Tic Tac Toy");
    static JButton btn;
    static JPanel panel;
    static int value =0;
    static String winner = null;
    static JLabel information=new JLabel("<html><i color=blue><u>Welcome to Tic Tac Toy game.<br/> <br/>Let's Start!</html>");
    
    
       public TicTacToy(){
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(this);
        frame.setContentPane(this);
        frame.setVisible(true);
        frame.setBackground(Color.yellow);
        block= new String[9];
        turn= "X";
         emptyBoard();
}
  
       

    
    static void winningText(){
        winner=checkWinner();
        if(winner !=null){
            if(winner.equalsIgnoreCase("Draw")){
                information.setText("<html><h1>Draw!</h1><br/>Tnx for playing.");
            }
            else information.setText("<html><<h1><i color=red>Congratulations!</i><br/>"+winner+" has won ...");
        }
    }
    
    static String checkWinner(){
        for(int i=0;i<8;i++){
            String line=null;
            switch(i){
                case 0:
                    line=block[0]+block[1]+block[2];
                    break;
                case 1:
                    line=block[3]+block[4]+block[5];
                    break;
                case 2:
                    line=block[6]+block[7]+block[8];
                    break;
                case 3:
                    line=block[0]+block[3]+block[6];
                    break;
                case 4:
                    line=block[2]+block[5]+block[8];
                    break;
                case 5:
                    line=block[0]+block[4]+block[8];
                    break;
                case 6:
                    line=block[2]+block[4]+block[6];
                    break;
                case 7:
                    line=block[1]+block[4]+block[7];
                    break;
            }
            if(line.equals("XXX"))
                return "X";
            else if(line.equals("OOO"))
                 return "O";
        }
        for(int i=0;i<9;i++){
            if(Arrays.asList(block).contains("Empty")){
                break;
            }
            else if(i==8){
                return "Draw";
            }
           
        }
        
         information.setText(turn+"'s turn!");
            return null;
            
    }
    
    static void emptyBoard(){
        
        frame.setLayout(null);
        information.setBounds(20,130,400,400);
        information.setBackground(Color.black);
        frame.add(information);
        
        int xIncrease=0;
        int yIncrease=-100;
        
        for(int i=0;i<9;i++) block[i]="Empty";
        for(int y=0;y<3;y++){
            xIncrease=0;
            yIncrease+=100;
            for(int x=0;x<3;x++){
                button.add(new JButton(""));
                button.get(value).setBounds(xIncrease,yIncrease,100,100);
                button.get(value).setOpaque(false);
                button.get(value).setContentAreaFilled(false);
                button.get(value).setBorderPainted(false);
                frame.add(button.get(value));
                
                button.get(value).addActionListener(new ActionListener() {
                    int val = value;
                    
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if(winner== null&& block[val]=="Empty"){
                         block[val]=turn;   
                         button.get(val).setText(turn);
                         if(turn.equals("X")){
                             turn="O";
                         }
                         else turn="X";
                         
                         winningText();
                        }
                        else if(winner !=null) information.setText("The Game has already been finished!");
                        
                        else information.setText("<html>The slot is already taken!<br/>Pick another slot.");
                        
                    }
                });
                value++;
                xIncrease+=100;
            }
            frame.setBounds(500,100,300,450);
            frame.setResizable(false);
        }
    }
public void paintComponent(Graphics g){
  super.paintComponent(g);
  int x=100;
  g.setColor(Color.black);
  
  for(int i=0;i<3;i++){
      g.drawLine(0, 0+x, 300, 0+x);
      if(i==3) break;
      g.drawLine(0+x,0,0+x,300);
      x+=100;
  }
}

/*
    
public static void main(String[] args) {
    
         TicTacToy tic=new TicTacToy();
        
   }

*/

}
