/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author harri
 */
public class loginFrame extends javax.swing.JFrame{

    /**
     * Creates new form loginFrame
     */
    public loginFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        selectAccLabel = new javax.swing.JLabel();
        loginComboBox = new javax.swing.JComboBox<>();
        usernameLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        usernameloginTF = new javax.swing.JTextField();
        passloginPF = new javax.swing.JPasswordField();
        mainLoginBtn = new javax.swing.JButton();
        mainExitBtn = new javax.swing.JButton();
        loginMessageLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setLayout(null);

        selectAccLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectAccLabel.setText("Select The Account:");
        jPanel1.add(selectAccLabel);
        selectAccLabel.setBounds(80, 20, 145, 61);

        loginComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Lecturer", "Student" }));
        jPanel1.add(loginComboBox);
        loginComboBox.setBounds(250, 20, 241, 61);

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usernameLabel.setText("Username:");
        jPanel1.add(usernameLabel);
        usernameLabel.setBounds(110, 100, 94, 41);

        passLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passLabel.setText("Password:");
        jPanel1.add(passLabel);
        passLabel.setBounds(110, 160, 94, 31);

        usernameloginTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(usernameloginTF);
        usernameloginTF.setBounds(210, 100, 307, 41);

        passloginPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(passloginPF);
        passloginPF.setBounds(210, 160, 307, 41);

        mainLoginBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mainLoginBtn.setText("Login");
        mainLoginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainLoginBtnMouseClicked(evt);
            }
        });
        mainLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainLoginBtnActionPerformed(evt);
            }
        });
        mainLoginBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mainLoginBtnKeyPressed(evt);
            }
        });
        jPanel1.add(mainLoginBtn);
        mainLoginBtn.setBounds(110, 280, 184, 59);

        mainExitBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mainExitBtn.setText("Exit");
        mainExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainExitBtnMouseClicked(evt);
            }
        });
        mainExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainExitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(mainExitBtn);
        mainExitBtn.setBounds(330, 280, 190, 59);

        loginMessageLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loginMessageLabel.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(loginMessageLabel);
        loginMessageLabel.setBounds(210, 220, 300, 41);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 140, 720, 390);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 721, 140);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void mainLoginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainLoginBtnMouseClicked
        // TODO add your handling code here:
        
        encryptdecrypt obj = new encryptdecrypt();
        
        String usertmp = usernameloginTF.getText();
        
        //String usertmpEnc = encryptdecrypt.toEncrypt(usertmp);
        String tmp1 = passloginPF.getText();
        String tmpPass = getMD5(tmp1);

        
       
        //Admin Account selected in ComboBox
        if(loginComboBox.getSelectedItem() == "Admin"){

          //System.out.println(usertmpEnc);
        }
        //Lecturer Account selected in ComboBox
        else if(loginComboBox.getSelectedItem() == "Lecturer"){

        } 
        //Student Account selected in ComboBox
        else if(loginComboBox.getSelectedItem() == "Student"){

        }
    }//GEN-LAST:event_mainLoginBtnMouseClicked

    private void mainLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainLoginBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_mainLoginBtnActionPerformed

    private void mainExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainExitBtnMouseClicked
        // TODO add your handling code here:

        System.exit(0);
    }//GEN-LAST:event_mainExitBtnMouseClicked

    private void mainExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainExitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainExitBtnActionPerformed

    private void mainLoginBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mainLoginBtnKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainLoginBtnKeyPressed

    public static String getMD5(String input){
        
        try{
            //Static getInstance method is called hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            byte[] messageDigest = md.digest(input.getBytes());
            
            //Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            
            //Convert message digest into hex value
            String hashtext = no.toString(16);
            while(hashtext.length() < 32){
                
                hashtext = "0" + hashtext;
            }
            return hashtext;
            
        }catch(NoSuchAlgorithmException e){
            
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> loginComboBox;
    private javax.swing.JLabel loginMessageLabel;
    private javax.swing.JButton mainExitBtn;
    private javax.swing.JButton mainLoginBtn;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passloginPF;
    private javax.swing.JLabel selectAccLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameloginTF;
    // End of variables declaration//GEN-END:variables
}
