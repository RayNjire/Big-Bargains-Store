package BeforeLogin;

import java.awt.Color;

/**Author and License
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author ray njire
 */
public class ChooseUserType extends javax.swing.JFrame 
{
    /**
     * Creates new form ChooseUserType
     * All Codes in here are run as the Form is being created
    */
    public ChooseUserType() 
    {
        //Personalized Default Frame Appearance
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
    }

    /**Default Instantiation Function
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PNL1 = new javax.swing.JPanel();
        LBLLogo = new javax.swing.JLabel();
        LBLAdmin = new javax.swing.JLabel();
        LBLOR = new javax.swing.JLabel();
        LBLCustomers = new javax.swing.JLabel();
        BTNBack = new javax.swing.JButton();
        BTNExit = new javax.swing.JButton();
        LBLBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOG IN AS:");
        setMinimumSize(new java.awt.Dimension(570, 318));
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        PNL1.setLayout(null);

        LBLLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Big Bargains Store Logo.jpg"))); // NOI18N
        PNL1.add(LBLLogo);
        LBLLogo.setBounds(30, 30, 300, 300);

        LBLAdmin.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        LBLAdmin.setForeground(new java.awt.Color(255, 255, 0));
        LBLAdmin.setText("ADMINISTRATOR");
        LBLAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLAdminMouseExited(evt);
            }
        });
        PNL1.add(LBLAdmin);
        LBLAdmin.setBounds(370, 70, 170, 30);

        LBLOR.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        LBLOR.setForeground(new java.awt.Color(255, 255, 255));
        LBLOR.setText("OR");
        PNL1.add(LBLOR);
        LBLOR.setBounds(440, 150, 30, 30);

        LBLCustomers.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        LBLCustomers.setForeground(new java.awt.Color(255, 255, 0));
        LBLCustomers.setText("CUSTOMER");
        LBLCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LBLCustomersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LBLCustomersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LBLCustomersMouseExited(evt);
            }
        });
        PNL1.add(LBLCustomers);
        LBLCustomers.setBounds(400, 250, 110, 22);

        BTNBack.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNBack.setText("Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });
        PNL1.add(BTNBack);
        BTNBack.setBounds(30, 360, 95, 40);

        BTNExit.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        BTNExit.setText("Exit");
        BTNExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNExitActionPerformed(evt);
            }
        });
        PNL1.add(BTNExit);
        BTNExit.setBounds(410, 360, 95, 40);

        LBLBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BigBargainPhotos/Background Photo.jpg"))); // NOI18N
        PNL1.add(LBLBackground);
        LBLBackground.setBounds(-6, -6, 900, 580);
        LBLBackground.getAccessibleContext().setAccessibleName("LBLBackGround");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        new IndexPage().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_BTNBackActionPerformed

    private void BTNExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNExitActionPerformed
        System.exit(0);
        
    }//GEN-LAST:event_BTNExitActionPerformed

    private void LBLAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminMouseClicked
        new AdminLogin().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_LBLAdminMouseClicked

    private void LBLAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminMouseEntered
        LBLAdmin.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLAdminMouseEntered

    private void LBLAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLAdminMouseExited
        LBLAdmin.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLAdminMouseExited

    private void LBLCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustomersMouseClicked
        new CustomerLogin().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_LBLCustomersMouseClicked

    private void LBLCustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustomersMouseEntered
        LBLCustomers.setForeground(Color.green);
        
    }//GEN-LAST:event_LBLCustomersMouseEntered

    private void LBLCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LBLCustomersMouseExited
        LBLCustomers.setForeground(Color.yellow);
        
    }//GEN-LAST:event_LBLCustomersMouseExited

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChooseUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseUserType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //<editor-fold defaultstate="collapsed" desc=" Code for Running this File (optional) ">
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new ChooseUserType().setVisible(true);
                
            }
        });
        //</editor-fold>
        
    }

    //Swing Objects Declared Here
    //<editor-fold defaultstate="collapsed" desc=" Declared Variable of the Swing Objects (do not modify) ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNExit;
    private javax.swing.JLabel LBLAdmin;
    private javax.swing.JLabel LBLBackground;
    private javax.swing.JLabel LBLCustomers;
    private javax.swing.JLabel LBLLogo;
    private javax.swing.JLabel LBLOR;
    private javax.swing.JPanel PNL1;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}