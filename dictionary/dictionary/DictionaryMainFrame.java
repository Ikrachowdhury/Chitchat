
package dictionary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DictionaryMainFrame extends JFrame{
    private static final long serial = 5L;
    

    public DictionaryMainFrame() {
        //JFrame frm= new JFrame();
        super("Dictionary");
        
        JMenuBar menub= new JMenuBar();
        
        JMenu menu= new JMenu("Dictionary");
        menub.add(menu);
        
        
        // Option Add
        JMenuItem itm= new JMenuItem("Add Word...");
        itm.setIcon(getImage("add.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F7"));
        menu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           addWord();
            }
        });
        
       
        
        //Delete Option
        itm= new JMenuItem("Delete Word...");
        itm.setIcon(getImage("delete.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F8"));
        menu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           delWord();
            }
        });
        
        menu.addSeparator(); //separate using single line
        
        //Search option
        itm= new JMenuItem("Search Meaning");
        itm.setIcon(getImage("search.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F9"));
        menu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
            searchWord();
            }
        });
        
        //List Option
        itm= new JMenuItem("Word List");
        itm.setIcon(getImage("list.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F10"));
        menu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           wordsList();
            }
        });
        
        menu.addSeparator();
        
        //Exit from this menu
        itm= new JMenuItem("Exit");
        menu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           exit();
            }
        });
  /*______________________________________________________________*/
        
        this.addStorageMenu(menub);
        this.setJMenuBar(menub);
        this.addContactMenu(menub);
        this.addHelpMenu(menub);
        
  /*______________________________________________________________*/
        
        JPanel p=new JPanel();
        JLabel label=new JLabel("<html> <i><u><h1 color=red> Instructions :</h1></u></i><br/> 1) To find a meaning of a word, click 'Search Meaning' icon <br/>"
                + "                from 'Dictionary' menu or from the given toolber.<br/>"
                + "              2) To add a word, click 'Add Word' icon & go to option 3.<br/>"
                + "              3) By clicking the 'Add Button' you have to click the 'Save to Disc' icon <br/>"
                + "                 from 'Storage' menu or from the given toolber.<br/> "
                + "              4) To delete a word, click 'Delete Word' icon .<br/>"
                + "              5) You can see the full list of word by clicking the 'Word List' icon.<br/>");
        p.add(label,BorderLayout.CENTER);
        this.add(p);
  /*______________________________________________________________*/  
        
        this.optionToolBar();
        
        Dictionary.loadFromDisc();   //Load dictionary from disc
        
        setVisible(true);
        setDefaultCloseOperation(DictionaryMainFrame.HIDE_ON_CLOSE);
        setBounds(300,100,500,350);
        setResizable(false);
                
        
    }
    
    public void centerToParent(JFrame parent,JFrame child){
        Dimension pr= parent.getSize();
        Dimension ch= child.getSize();
        int x= (int) (pr.getWidth()-ch.getWidth())/2;
        int y= (int) (pr.getHeight() - ch.getHeight())/2;
        child.setLocation(x,y);
    }
    public void addWord(){
            
        ImplementWord i= new ImplementWord();
        centerToParent(DictionaryMainFrame.this,i);
        i.setVisible(true);
        }
    
    public void delWord(){
        DeleteListedWord d= new DeleteListedWord();
        centerToParent(DictionaryMainFrame.this,d);
        d.setVisible(true);
    }
    
    public void searchWord(){
        WordSearch s= new WordSearch();
        centerToParent(DictionaryMainFrame.this,s);
        s.setVisible(true);
    }
    
    public void wordsList(){
        DisplayWord dw= new DisplayWord();
        dw.setVisible(true);
        centerToParent(DictionaryMainFrame.this,dw);
    }
    
    public ImageIcon getImage(String filename){
        return new ImageIcon(this.getClass().getResource(filename));
    }
    
    public void exit(){
        if(Dictionary.isModified()){
            int optn= JOptionPane.showConfirmDialog(DictionaryMainFrame.this, "Do you want to write all pending changes to disc and then exit?","Save",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(optn==JOptionPane.YES_OPTION){ //Exit after Save
                Dictionary.saveToDisk();
                System.exit(0);
            }
            else if(optn==JOptionPane.NO_OPTION){ //Exit without Save
                System.exit(0);
            }
            else {    //if Cancel then do not Exit
                System.exit(0);
            }
        }
    }
    
    public void optionToolBar(){
        JToolBar tlb= new JToolBar();
        
        JButton j= new JButton(getImage("add.gif"));
        j.setPreferredSize(new Dimension(40,40));
        tlb.add(j);
        j.setToolTipText("Add Word");
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           addWord();
            }
        });
        
        j= new JButton(getImage("delete.gif"));
        j.setPreferredSize(new Dimension(40,40));
        tlb.add(j);
        j.setToolTipText("Delete Word");
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           delWord();
            }
        });
        
        j= new JButton(getImage("search.gif"));
        j.setPreferredSize(new Dimension(40,40));
        tlb.add(j);
        j.setToolTipText("Search Meaning");
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                searchWord();
            }
        });
        
        j= new JButton(getImage("list.gif"));
        j.setPreferredSize(new Dimension(40,40));
        tlb.add(j);
        j.setToolTipText("Word List");
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                wordsList();
            }
        });
        
        tlb.addSeparator();
        tlb.addSeparator();
        
        j= new JButton(getImage("save1.gif"));
        j.setToolTipText("Save to Disc");
        tlb.add(j);
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                   Dictionary.saveToDisk();
            }
        });
        
        j= new JButton(getImage("load.gif"));
        j.setToolTipText("Load from Disc");
        tlb.add(j);
        j.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           Dictionary.loadFromDisc();
            }
        });
        
        getContentPane().add(tlb,BorderLayout.SOUTH);
    }
    
    public void addStorageMenu(JMenuBar jmb){
        
        JMenu mnu= new JMenu("Storage");
        JMenuItem itm= new JMenuItem("Save Dictionary");
        itm.setIcon(getImage("save1.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F11"));
        mnu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           boolean save= Dictionary.saveToDisk();
           if(save){
               JOptionPane.showMessageDialog(DictionaryMainFrame.this,"Saved dictionary successfully!","Save Dictonary",JOptionPane.INFORMATION_MESSAGE);
           }
           else {
               JOptionPane.showMessageDialog(DictionaryMainFrame.this, "Error! " , "Save Word",JOptionPane.ERROR_MESSAGE);
           }
            }
        });
        
        itm= new JMenuItem("Load Dictionary");
        itm.setIcon(getImage("load.gif"));
        mnu.add(itm);
        itm.setAccelerator(KeyStroke.getKeyStroke("F12"));
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           boolean load= Dictionary.loadFromDisc();
           if(load){
               JOptionPane.showMessageDialog(DictionaryMainFrame.this, "Loaded dictionary successfully!","Load Dictionary",JOptionPane.INFORMATION_MESSAGE);
           }
           else {
               JOptionPane.showMessageDialog(DictionaryMainFrame.this, "Error! ","Load Dictionary",JOptionPane.ERROR_MESSAGE);
           }
            }
        });
        
        jmb.add(mnu);
    }
    
    public void addContactMenu(JMenuBar jmb){
        
        JMenu mnu= new JMenu("Contact");
        JMenuItem itm= new JMenuItem("Cell Phone");
        itm.setIcon(getImage("phone.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F1"));
        mnu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           
               JOptionPane.showMessageDialog(DictionaryMainFrame.this,"+88 01930-878889","Cell Phone Number",JOptionPane.INFORMATION_MESSAGE);
          
            }
        });
        
        itm= new JMenuItem("WhatsApp");
        itm.setIcon(getImage("wapp.jpeg"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F2"));
        mnu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           
               JOptionPane.showMessageDialog(DictionaryMainFrame.this,"+88 01930-878889","WhatsApp Number",JOptionPane.INFORMATION_MESSAGE);
          
            }
        });
        
        itm= new JMenuItem("E-mail");
        itm.setIcon(getImage("email.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F3"));
        mnu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           
               JOptionPane.showMessageDialog(DictionaryMainFrame.this,"ranaswe2@gmail.com","E-mail Address",JOptionPane.INFORMATION_MESSAGE);
          
            }
        });
        
        jmb.add(mnu);
    }
    
    public void addHelpMenu(JMenuBar jmb){
        
        JMenu mnu= new JMenu("Help");
        JMenuItem itm= new JMenuItem("Question Section");
       // itm.setIcon(getImage("save1.gif"));
        itm.setAccelerator(KeyStroke.getKeyStroke("F4"));
        mnu.add(itm);
        itm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
           
               JOptionPane.showMessageDialog(DictionaryMainFrame.this,"<html>If you have any question,<br/> please, keep it for your self.<br/> I'm not google.","Dhurrrr !!!",JOptionPane.INFORMATION_MESSAGE);
          
            }
        });
        
        jmb.add(mnu);
    }
    
    public static void main(String[] args) {
        DictionaryMainFrame a=new DictionaryMainFrame();
        
    }
}
