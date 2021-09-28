
package calculator;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;



public class Converter extends javax.swing.JFrame {

   
   
  
    public Converter() {
        super("Converter");
        UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.GREEN));
        initComponents();
        
         jtf1.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_PERIOD)|| (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                   // JOptionPane.showMessageDialog(null, " Please ! Give correct input.");
                     lbl2.setText("* Please ! Give correct input.");
                   
                }
               else if((ch >='0' && ch <='9' )){
                    // e.consume();
                   // JOptionPane.showMessageDialog(null, " Please ! Give correct input.");
                     lbl2.setText(" ");
                   
                }
            }
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf1 = new javax.swing.JTextField();
        lbl = new javax.swing.JLabel();
        jtf2 = new javax.swing.JTextField();
        kmm = new javax.swing.JButton();
        mkm = new javax.swing.JButton();
        mcm = new javax.swing.JButton();
        cmm = new javax.swing.JButton();
        kmhms = new javax.swing.JButton();
        ftm = new javax.swing.JButton();
        cmin = new javax.swing.JButton();
        inft = new javax.swing.JButton();
        hrsc = new javax.swing.JButton();
        schr = new javax.swing.JButton();
        lbl2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Input");

        jtf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf1ActionPerformed(evt);
            }
        });

        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setText("=");

        kmm.setBackground(new java.awt.Color(1, 51, 20));
        kmm.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        kmm.setForeground(new java.awt.Color(255, 255, 255));
        kmm.setText("km -> m");
        kmm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmmActionPerformed(evt);
            }
        });

        mkm.setBackground(new java.awt.Color(1, 51, 20));
        mkm.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        mkm.setForeground(new java.awt.Color(255, 255, 255));
        mkm.setText("m -> km");
        mkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mkmActionPerformed(evt);
            }
        });

        mcm.setBackground(new java.awt.Color(1, 51, 20));
        mcm.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        mcm.setForeground(new java.awt.Color(255, 255, 255));
        mcm.setText("m -> cm");
        mcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcmActionPerformed(evt);
            }
        });

        cmm.setBackground(new java.awt.Color(1, 51, 20));
        cmm.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        cmm.setForeground(new java.awt.Color(255, 255, 255));
        cmm.setText("cm -> m");
        cmm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmmActionPerformed(evt);
            }
        });

        kmhms.setBackground(new java.awt.Color(1, 51, 20));
        kmhms.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        kmhms.setForeground(new java.awt.Color(255, 255, 255));
        kmhms.setText("km/h -> m/s");
        kmhms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kmhmsActionPerformed(evt);
            }
        });

        ftm.setBackground(new java.awt.Color(1, 51, 20));
        ftm.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        ftm.setForeground(new java.awt.Color(255, 255, 255));
        ftm.setText("ft -> m");
        ftm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftmActionPerformed(evt);
            }
        });

        cmin.setBackground(new java.awt.Color(1, 51, 20));
        cmin.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        cmin.setForeground(new java.awt.Color(255, 255, 255));
        cmin.setText("cm -> inch");
        cmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cminActionPerformed(evt);
            }
        });

        inft.setBackground(new java.awt.Color(1, 51, 20));
        inft.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        inft.setForeground(new java.awt.Color(255, 255, 255));
        inft.setText("inch -> ft");
        inft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inftActionPerformed(evt);
            }
        });

        hrsc.setBackground(new java.awt.Color(1, 51, 20));
        hrsc.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        hrsc.setForeground(new java.awt.Color(255, 255, 255));
        hrsc.setText("hr -> sc");
        hrsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrscActionPerformed(evt);
            }
        });

        schr.setBackground(new java.awt.Color(1, 51, 20));
        schr.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        schr.setForeground(new java.awt.Color(255, 255, 255));
        schr.setText("sc -> hr");
        schr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schrActionPerformed(evt);
            }
        });

        lbl2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kmm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kmhms, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mkm, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mcm, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmm, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hrsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schr, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inft, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(kmm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mkm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mcm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kmhms))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hrsc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(schr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftm)))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kmmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmmActionPerformed
       
        if(jtf1.getText() != null){
            lbl.setText(kmm.getText());
          
            double t2= Double.valueOf(jtf1.getText())*1000;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_kmmActionPerformed

    private void mkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mkmActionPerformed
       
        if(jtf1.getText() != null){
            lbl.setText(mkm.getText());
          
            double t2= Double.valueOf(jtf1.getText())*0.001;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_mkmActionPerformed

    private void kmhmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kmhmsActionPerformed
        
        if(jtf1.getText() != null){
            lbl.setText(kmhms.getText());
          
            double t2= Double.valueOf(jtf1.getText())/3.6;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_kmhmsActionPerformed

    private void mcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcmActionPerformed
       
        if(jtf1.getText() != null){
            lbl.setText(mcm.getText());
          
            double t2= Double.valueOf(jtf1.getText())*100;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_mcmActionPerformed

    private void cmmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmmActionPerformed
        
        if(jtf1.getText() != null){
            lbl.setText(cmm.getText());
          
            double t2= Double.valueOf(jtf1.getText())*0.01;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_cmmActionPerformed

    private void ftmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftmActionPerformed
         
        if(jtf1.getText() != null){
            lbl.setText(ftm.getText());
          
            double t2= Double.valueOf(jtf1.getText())*0.3048;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_ftmActionPerformed

    private void cminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cminActionPerformed
         
        if(jtf1.getText() != null){
            lbl.setText(cmin.getText());
          
            double t2= Double.valueOf(jtf1.getText())/2.54;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_cminActionPerformed

    private void inftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inftActionPerformed
         
        if(jtf1.getText() != null){
            lbl.setText(inft.getText());
          
            double t2= Double.valueOf(jtf1.getText())/12;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_inftActionPerformed

    private void hrscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrscActionPerformed
         
        if(jtf1.getText() != null){
            lbl.setText(hrsc.getText());
          
            double t2= Double.valueOf(jtf1.getText())*3600;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_hrscActionPerformed

    private void schrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schrActionPerformed
         
        if(jtf1.getText() != null){
            lbl.setText(schr.getText());
          
            double t2= Double.valueOf(jtf1.getText())/3600;
            jtf2.setText(String.valueOf(t2));
        }
        
    }//GEN-LAST:event_schrActionPerformed

    private void jtf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf1ActionPerformed
       
    }//GEN-LAST:event_jtf1ActionPerformed

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Converter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Converter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmin;
    private javax.swing.JButton cmm;
    private javax.swing.JButton ftm;
    private javax.swing.JButton hrsc;
    private javax.swing.JButton inft;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jtf1;
    private javax.swing.JTextField jtf2;
    private javax.swing.JButton kmhms;
    private javax.swing.JButton kmm;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl2;
    private javax.swing.JButton mcm;
    private javax.swing.JButton mkm;
    private javax.swing.JButton schr;
    // End of variables declaration//GEN-END:variables
}
