package uw2l;

import java.awt.BorderLayout;
import static java.awt.SystemColor.text;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class uw2lFrame extends javax.swing.JFrame {

    /**
     * Creates new form uw2lFrame
     */
    student std = new student();
    DefaultTableModel model;
    DefaultTableModel mod312;
    DefaultTableModel mod331;
    DefaultTableModel mod121;
    DefaultTableModel mod368;
    DefaultTableModel stud312;
    DefaultTableModel stud331;
    DefaultTableModel stud121;
    DefaultTableModel stud368;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
    public uw2lFrame() {
        initComponents();
        //Show_Users_Table();
        std.fillUserTable(usersTable, "");
        model = (DefaultTableModel)usersTable.getModel();
        mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
        mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
        mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
        mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
        stud312 = (DefaultTableModel)studentVidTable.getModel();
        stud331 = (DefaultTableModel)studentVidTable.getModel();
        stud121 = (DefaultTableModel)studentVidTable.getModel();
        stud368 = (DefaultTableModel)studentVidTable.getModel();
        mainPanel.setVisible(true);
        adminPanel.setVisible(false);
        studentPanel.setVisible(false);
        studModSelectPanel.setVisible(false);
        lecModSelectPanel.setVisible(false);
        lecturerPanel.setVisible(false);
        messageLabel.setVisible(false);
        mod1Btn.setVisible(false);
        mod2Btn.setVisible(false);
        mod3Btn.setVisible(false);
        mod4Btn.setVisible(false);
        mod2Btn1.setVisible(false);
        mod1Btn1.setVisible(false);
        mod3Btn1.setVisible(false);
        mod4Btn1.setVisible(false);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        lecVidPlayPanel.setVisible(false);
        playingVidPanel.setVisible(false);
        playingVidPanel = new javax.swing.JPanel();
        
    }

    //Function To Connect To MySQL Database
    public Connection getConnection()
    {
        Connection con = null;
        
        try{
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/project_database","root","");
            //Connected
            JOptionPane.showMessageDialog(null, "Connected");
            return con;
            
        }catch(SQLException ex){
            
           Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE,null,ex);
           //Not conencted
           JOptionPane.showMessageDialog(null, "Not Connected");
           return null;
        }
       
    }
    
    // Fill ArrayList with the Data for Admin
    public ArrayList<adminList> getAdminList()
    {
        
        ArrayList<adminList> admin_List = new ArrayList<adminList>();
        Connection con = getConnection();
        String query = "SELECT * FROM admintable";
        
        Statement st;
        ResultSet rs;
        
        try{
            
            st= con.createStatement();
            rs = st.executeQuery(query);
            adminList adminInfo;
            
            while(rs.next()){
                
                adminInfo = new adminList(rs.getInt("id"),rs.getString("name"),
                        rs.getString("password"),rs.getString("module1"),
                        rs.getString("module2"),rs.getString("module3"),
                        rs.getString("module4"));
                admin_List.add(adminInfo);
            }
            
        }catch(SQLException ex){
            
            Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return admin_List;
    }
    
    // Fill ArrayList with the Data for Lecturer
    public ArrayList<lecturerList> getLecList()
    {
        
        ArrayList<lecturerList> lecturer_List = new ArrayList<lecturerList>();
        Connection con = getConnection();
        String query = "SELECT * FROM lecturerdata";
        
        Statement st;
        ResultSet rs;
        
        try{
            
            st= con.createStatement();
            rs = st.executeQuery(query);
            lecturerList lecInfo;
            
            while(rs.next()){
                
                lecInfo = new lecturerList(rs.getInt("id"),rs.getString("name"),
                        rs.getString("password"),rs.getString("module1"),
                        rs.getString("module2"),rs.getString("module3"));
                lecturer_List.add(lecInfo);
            }
            
        }catch(SQLException ex){
            
            Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return lecturer_List;
    }
    
    // Fill ArrayList with the Data for Student
    public ArrayList<studentList> getStudentList()
    {
        
        ArrayList<studentList> student_List = new ArrayList<studentList>();
        Connection con = getConnection();
        String query = "SELECT * FROM studentdata";
        
        Statement st;
        ResultSet rs;
        
        try{
            
            st= con.createStatement();
            rs = st.executeQuery(query);
            studentList studentInfo;
            
            while(rs.next()){
                
                studentInfo = new studentList(rs.getInt("id"),rs.getString("name"),
                        rs.getString("uowno"),rs.getString("password"),
                        rs.getString("module1"),rs.getString("module2"),
                        rs.getString("module3"));
                student_List.add(studentInfo);
            }
            
        }catch(SQLException ex){
            
            Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return student_List;
    }

    public void Show_Users_Table(){
        
        ArrayList<studentList> list = getStudentList();
        DefaultTableModel model = (DefaultTableModel)usersTable.getModel();
        
        //Clear JTable
        model.setRowCount(0);
        
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++){
            
            row[0] = list.get(i).getName();
            row[1] = list.get(i).getUOWno();
            row[2] = list.get(i).getMod1();
            row[3] = list.get(i).getMod2();
            row[4] = list.get(i).getMod3();
            
            model.addRow(row);
        }
        
    }
    
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        selectAccLabel = new javax.swing.JLabel();
        mainComboBox = new javax.swing.JComboBox<>();
        usernameLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passPF = new javax.swing.JPasswordField();
        mainloginBtn = new javax.swing.JButton();
        mainExitBtn = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        adminPanel = new javax.swing.JPanel();
        addUserBtn = new javax.swing.JButton();
        editUserBtn = new javax.swing.JButton();
        deleteUserBtn = new javax.swing.JButton();
        adminPanelExitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        logoutBtn = new javax.swing.JButton();
        managerUsersLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        uownoLabel = new javax.swing.JLabel();
        module1Label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mod3UserTF = new javax.swing.JTextField();
        managingUserTF = new javax.swing.JTextField();
        uownoUserTF = new javax.swing.JTextField();
        mod1UserTF = new javax.swing.JTextField();
        mod2UserTF = new javax.swing.JTextField();
        studentPanel = new javax.swing.JPanel();
        studentPanelLabel = new javax.swing.JLabel();
        studWatchVidBtn = new javax.swing.JButton();
        studLogoutBtn = new javax.swing.JButton();
        studExitBtn = new javax.swing.JButton();
        studDLVidBtn = new javax.swing.JButton();
        studbackBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentVidTable = new javax.swing.JTable();
        lecturerPanel = new javax.swing.JPanel();
        watchVidBtn = new javax.swing.JButton();
        dlVidBtn = new javax.swing.JButton();
        uploadVidBtn = new javax.swing.JButton();
        deleteVidBtn = new javax.swing.JButton();
        editVidBtn = new javax.swing.JButton();
        lecLogoutBtn = new javax.swing.JButton();
        lecExitBtn = new javax.swing.JButton();
        lecModLabel = new javax.swing.JLabel();
        lecBackBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        lecPanLecVidTable = new javax.swing.JTable();
        lecModSelectPanel = new javax.swing.JPanel();
        mod1Btn = new javax.swing.JButton();
        mod4Btn = new javax.swing.JButton();
        mod2Btn = new javax.swing.JButton();
        mod3Btn = new javax.swing.JButton();
        lecLabelPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        studModSelectPanel = new javax.swing.JPanel();
        mod1Btn1 = new javax.swing.JButton();
        mod4Btn1 = new javax.swing.JButton();
        mod2Btn1 = new javax.swing.JButton();
        mod3Btn1 = new javax.swing.JButton();
        lecLabelPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lecVidPlayPanel = new javax.swing.JPanel();
        playBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        pauseBtn = new javax.swing.JButton();
        rewindBtn = new javax.swing.JButton();
        skipBtn = new javax.swing.JButton();
        vidTitleLabel = new javax.swing.JLabel();
        playingVidPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 153, 0));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        mainPanel.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("UWatch2Learn");
        mainPanel.add(titleLabel);
        titleLabel.setBounds(290, 60, 381, 76);

        selectAccLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selectAccLabel.setText("Select The Account:");
        mainPanel.add(selectAccLabel);
        selectAccLabel.setBounds(150, 190, 145, 61);

        mainComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mainComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Lecturer", "Student" }));
        mainPanel.add(mainComboBox);
        mainComboBox.setBounds(330, 190, 241, 61);

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usernameLabel.setText("Username:");
        mainPanel.add(usernameLabel);
        usernameLabel.setBounds(200, 290, 94, 41);

        passLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passLabel.setText("Password:");
        mainPanel.add(passLabel);
        passLabel.setBounds(200, 370, 94, 31);

        usernameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainPanel.add(usernameTF);
        usernameTF.setBounds(330, 290, 307, 41);

        passPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainPanel.add(passPF);
        passPF.setBounds(330, 360, 307, 41);

        mainloginBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mainloginBtn.setText("Login");
        mainloginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainloginBtnMouseClicked(evt);
            }
        });
        mainloginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainloginBtnActionPerformed(evt);
            }
        });
        mainPanel.add(mainloginBtn);
        mainloginBtn.setBounds(250, 460, 184, 59);

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
        mainPanel.add(mainExitBtn);
        mainExitBtn.setBounds(500, 460, 190, 59);
        mainPanel.add(messageLabel);
        messageLabel.setBounds(690, 280, 238, 41);

        adminPanel.setBackground(new java.awt.Color(51, 255, 255));
        adminPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        adminPanel.setLayout(null);

        addUserBtn.setText("Add User");
        addUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserBtnActionPerformed(evt);
            }
        });
        adminPanel.add(addUserBtn);
        addUserBtn.setBounds(30, 530, 100, 54);

        editUserBtn.setText("Edit User");
        editUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserBtnActionPerformed(evt);
            }
        });
        adminPanel.add(editUserBtn);
        editUserBtn.setBounds(140, 530, 110, 54);

        deleteUserBtn.setText("Delete User");
        deleteUserBtn.setPreferredSize(new java.awt.Dimension(77, 23));
        deleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserBtnActionPerformed(evt);
            }
        });
        adminPanel.add(deleteUserBtn);
        deleteUserBtn.setBounds(260, 530, 110, 54);

        adminPanelExitBtn.setText("Exit");
        adminPanelExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPanelExitBtnActionPerformed(evt);
            }
        });
        adminPanel.add(adminPanelExitBtn);
        adminPanelExitBtn.setBounds(500, 530, 110, 54);

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "UOWno", "Module1", "Module2", "Module3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        usersTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usersTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        adminPanel.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 620, 430);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        adminPanel.add(logoutBtn);
        logoutBtn.setBounds(380, 530, 110, 54);

        managerUsersLabel.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        managerUsersLabel.setText("Manage Users");
        adminPanel.add(managerUsersLabel);
        managerUsersLabel.setBounds(30, 20, 210, 30);

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameLabel.setText("Name:");
        adminPanel.add(nameLabel);
        nameLabel.setBounds(680, 80, 60, 40);

        uownoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        uownoLabel.setText("UOWno:");
        adminPanel.add(uownoLabel);
        uownoLabel.setBounds(680, 140, 70, 40);

        module1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        module1Label.setText("Module1:");
        adminPanel.add(module1Label);
        module1Label.setBounds(680, 210, 70, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Module2:");
        adminPanel.add(jLabel2);
        jLabel2.setBounds(680, 280, 70, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Module3:");
        adminPanel.add(jLabel3);
        jLabel3.setBounds(680, 350, 70, 40);
        adminPanel.add(mod3UserTF);
        mod3UserTF.setBounds(770, 350, 190, 40);
        adminPanel.add(managingUserTF);
        managingUserTF.setBounds(770, 80, 190, 40);
        adminPanel.add(uownoUserTF);
        uownoUserTF.setBounds(770, 140, 190, 40);
        adminPanel.add(mod1UserTF);
        mod1UserTF.setBounds(770, 210, 190, 40);
        adminPanel.add(mod2UserTF);
        mod2UserTF.setBounds(770, 280, 190, 40);

        studentPanel.setBackground(new java.awt.Color(0, 153, 204));
        studentPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        studentPanel.setLayout(null);

        studentPanelLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        studentPanelLabel.setText("Modules");
        studentPanel.add(studentPanelLabel);
        studentPanelLabel.setBounds(790, 40, 130, 50);

        studWatchVidBtn.setText("Watch");
        studentPanel.add(studWatchVidBtn);
        studWatchVidBtn.setBounds(790, 140, 130, 50);

        studLogoutBtn.setText("Logout");
        studLogoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studLogoutBtnMouseClicked(evt);
            }
        });
        studentPanel.add(studLogoutBtn);
        studLogoutBtn.setBounds(790, 390, 130, 50);

        studExitBtn.setText("Exit");
        studExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studExitBtnMouseClicked(evt);
            }
        });
        studExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studExitBtnActionPerformed(evt);
            }
        });
        studentPanel.add(studExitBtn);
        studExitBtn.setBounds(790, 470, 130, 50);

        studDLVidBtn.setText("Download");
        studentPanel.add(studDLVidBtn);
        studDLVidBtn.setBounds(790, 230, 130, 50);

        studbackBtn.setText("Back");
        studentPanel.add(studbackBtn);
        studbackBtn.setBounds(790, 310, 130, 50);

        studentVidTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(studentVidTable);

        studentPanel.add(jScrollPane2);
        jScrollPane2.setBounds(10, 30, 740, 540);

        lecturerPanel.setBackground(new java.awt.Color(0, 204, 255));
        lecturerPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        lecturerPanel.setLayout(null);

        watchVidBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        watchVidBtn.setText("Watch Video");
        watchVidBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchVidBtnActionPerformed(evt);
            }
        });
        lecturerPanel.add(watchVidBtn);
        watchVidBtn.setBounds(20, 80, 170, 40);

        dlVidBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dlVidBtn.setText("Download");
        lecturerPanel.add(dlVidBtn);
        dlVidBtn.setBounds(20, 140, 170, 40);

        uploadVidBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        uploadVidBtn.setText("Upload");
        lecturerPanel.add(uploadVidBtn);
        uploadVidBtn.setBounds(20, 200, 170, 40);

        deleteVidBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteVidBtn.setText("Delete Video");
        lecturerPanel.add(deleteVidBtn);
        deleteVidBtn.setBounds(20, 270, 170, 40);

        editVidBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editVidBtn.setText("Edit Video");
        lecturerPanel.add(editVidBtn);
        editVidBtn.setBounds(20, 340, 170, 40);

        lecLogoutBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lecLogoutBtn.setText("Logout");
        lecLogoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lecLogoutBtnMouseClicked(evt);
            }
        });
        lecturerPanel.add(lecLogoutBtn);
        lecLogoutBtn.setBounds(20, 470, 170, 40);

        lecExitBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lecExitBtn.setText("Exit");
        lecExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lecExitBtnMouseClicked(evt);
            }
        });
        lecturerPanel.add(lecExitBtn);
        lecExitBtn.setBounds(20, 540, 170, 40);

        lecModLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lecturerPanel.add(lecModLabel);
        lecModLabel.setBounds(20, 10, 200, 60);

        lecBackBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lecBackBtn.setText("Back");
        lecturerPanel.add(lecBackBtn);
        lecBackBtn.setBounds(20, 410, 170, 40);

        lecPanLecVidTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(lecPanLecVidTable);

        lecturerPanel.add(jScrollPane6);
        jScrollPane6.setBounds(210, 70, 740, 510);

        lecModSelectPanel.setBackground(new java.awt.Color(0, 255, 255));
        lecModSelectPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        lecModSelectPanel.setLayout(null);

        mod1Btn.setBackground(new java.awt.Color(204, 0, 255));
        mod1Btn.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod1Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod1BtnMouseClicked(evt);
            }
        });
        lecModSelectPanel.add(mod1Btn);
        mod1Btn.setBounds(10, 440, 470, 165);

        mod4Btn.setBackground(new java.awt.Color(0, 153, 255));
        mod4Btn.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod4Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod4BtnMouseClicked(evt);
            }
        });
        lecModSelectPanel.add(mod4Btn);
        mod4Btn.setBounds(510, 270, 460, 165);

        mod2Btn.setBackground(new java.awt.Color(0, 255, 0));
        mod2Btn.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod2Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod2BtnMouseClicked(evt);
            }
        });
        lecModSelectPanel.add(mod2Btn);
        mod2Btn.setBounds(510, 440, 460, 165);

        mod3Btn.setBackground(new java.awt.Color(255, 0, 0));
        mod3Btn.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod3Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod3BtnMouseClicked(evt);
            }
        });
        lecModSelectPanel.add(mod3Btn);
        mod3Btn.setBounds(10, 270, 470, 165);

        lecLabelPanel.setBackground(new java.awt.Color(0, 255, 102));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 80)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Select Module");

        javax.swing.GroupLayout lecLabelPanelLayout = new javax.swing.GroupLayout(lecLabelPanel);
        lecLabelPanel.setLayout(lecLabelPanelLayout);
        lecLabelPanelLayout.setHorizontalGroup(
            lecLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        lecLabelPanelLayout.setVerticalGroup(
            lecLabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lecLabelPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        lecModSelectPanel.add(lecLabelPanel);
        lecLabelPanel.setBounds(0, 0, 980, 260);

        studModSelectPanel.setBackground(new java.awt.Color(204, 0, 204));
        studModSelectPanel.setPreferredSize(new java.awt.Dimension(980, 610));
        studModSelectPanel.setLayout(null);

        mod1Btn1.setBackground(new java.awt.Color(204, 0, 255));
        mod1Btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod1Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod1Btn1MouseClicked(evt);
            }
        });
        studModSelectPanel.add(mod1Btn1);
        mod1Btn1.setBounds(10, 440, 470, 165);

        mod4Btn1.setBackground(new java.awt.Color(0, 153, 255));
        mod4Btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod4Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod4Btn1MouseClicked(evt);
            }
        });
        studModSelectPanel.add(mod4Btn1);
        mod4Btn1.setBounds(510, 270, 460, 165);

        mod2Btn1.setBackground(new java.awt.Color(0, 255, 0));
        mod2Btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod2Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod2Btn1MouseClicked(evt);
            }
        });
        studModSelectPanel.add(mod2Btn1);
        mod2Btn1.setBounds(510, 440, 460, 165);

        mod3Btn1.setBackground(new java.awt.Color(255, 0, 0));
        mod3Btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mod3Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mod3Btn1MouseClicked(evt);
            }
        });
        studModSelectPanel.add(mod3Btn1);
        mod3Btn1.setBounds(10, 270, 470, 165);

        lecLabelPanel1.setBackground(new java.awt.Color(0, 255, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 80)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Select Module");

        javax.swing.GroupLayout lecLabelPanel1Layout = new javax.swing.GroupLayout(lecLabelPanel1);
        lecLabelPanel1.setLayout(lecLabelPanel1Layout);
        lecLabelPanel1Layout.setHorizontalGroup(
            lecLabelPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        lecLabelPanel1Layout.setVerticalGroup(
            lecLabelPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lecLabelPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        studModSelectPanel.add(lecLabelPanel1);
        lecLabelPanel1.setBounds(0, 0, 980, 260);

        lecVidPlayPanel.setBackground(new java.awt.Color(0, 204, 255));
        lecVidPlayPanel.setMinimumSize(new java.awt.Dimension(980, 610));

        playBtn.setText("Play");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("Stop");
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }
        });

        pauseBtn.setText("Pause");
        pauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseBtnActionPerformed(evt);
            }
        });

        rewindBtn.setText("Rewind");
        rewindBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rewindBtnActionPerformed(evt);
            }
        });

        skipBtn.setText("Skip");
        skipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipBtnActionPerformed(evt);
            }
        });

        vidTitleLabel.setText("1");

        javax.swing.GroupLayout playingVidPanelLayout = new javax.swing.GroupLayout(playingVidPanel);
        playingVidPanel.setLayout(playingVidPanelLayout);
        playingVidPanelLayout.setHorizontalGroup(
            playingVidPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
        );
        playingVidPanelLayout.setVerticalGroup(
            playingVidPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lecVidPlayPanelLayout = new javax.swing.GroupLayout(lecVidPlayPanel);
        lecVidPlayPanel.setLayout(lecVidPlayPanelLayout);
        lecVidPlayPanelLayout.setHorizontalGroup(
            lecVidPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lecVidPlayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lecVidPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playingVidPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vidTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lecVidPlayPanelLayout.createSequentialGroup()
                        .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rewindBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(skipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(186, Short.MAX_VALUE))
        );
        lecVidPlayPanelLayout.setVerticalGroup(
            lecVidPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lecVidPlayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vidTitleLabel)
                .addGap(18, 18, 18)
                .addComponent(playingVidPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lecVidPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(skipBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(rewindBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pauseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(studentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecturerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecModSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(studModSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecVidPlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(studentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecturerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecModSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(studModSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lecVidPlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainExitBtnMouseClicked
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_mainExitBtnMouseClicked

    private void mainloginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainloginBtnMouseClicked
        // TODO add your handling code here:
       Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
      
        String usertmp = usernameTF.getText(); 
        String tmp1 = passPF.getText();
        String tmpPass = getMD5(tmp1);
        
        String selectQueryA = "SELECT `name`,`password` FROM `admintable` WHERE `Name` = ? AND `Password` = ?";
        
        //Admin Account selected in ComboBox 
        if(mainComboBox.getSelectedItem() == "Admin"){
           
          try{
              
            ps = con.prepareStatement(selectQueryA);
            ps.setString(1, usertmp);
            ps.setString(2, tmpPass);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                
                mainPanel.setVisible(false);
                adminPanel.setVisible(true);    
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid username/password");
            }
            
        }catch(SQLException ex ){
            
            Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        }
        //Lecturer Account selected in ComboBox 
        else if(mainComboBox.getSelectedItem() == "Lecturer"){

            try{
                ps = con.prepareStatement("SELECT `name`,`password`,`module1`,`module2`,`module3` FROM `lecturerdata` WHERE `name` = ? AND `password` = ?");
                ps.setString(1, usertmp);
                ps.setString(2, tmpPass);
                          
                rs = ps.executeQuery();
            
                if(rs.next()){
                
                    //Module 1
                    if(rs.getString("module1").equals("CSCI312") ){
                        
                        mod1Btn.setVisible(true);
                        mod1Btn.setText("CSCI312");
                    }
                    else if(rs.getString("module1").equals("CSCI331")){
                        
                        mod1Btn.setVisible(true);
                        mod1Btn.setText("CSCI331");
                    }
                    else if(rs.getString("module1").equals("CSCI368")){
                        
                        mod1Btn.setVisible(true);
                        mod1Btn.setText("CSCI368");
                    }
                    else if(rs.getString("module1").equals("ISIT121")){
                        
                        mod1Btn.setVisible(true);
                        mod1Btn.setText("ISIT121");
                    }
                    else if(rs.getString("module1").equals("")){
                        
                        mod1Btn.setVisible(false);
                    }
                    //Module 2
                    if(rs.getString("module2").equals("CSCI312") ){
                        
                        mod2Btn.setVisible(true);
                        mod2Btn.setText("CSCI312");
                    }
                    else if(rs.getString("module2").equals("CSCI331")){
                        
                        mod2Btn.setVisible(true);
                        mod2Btn.setText("CSCI331");
                    }
                    else if(rs.getString("module2").equals("CSCI368")){
                        
                        mod2Btn.setVisible(true);
                        mod2Btn.setText("CSCI368");
                    }
                    else if(rs.getString("module2").equals("ISIT121")){
                        
                        mod2Btn.setVisible(true);
                        mod2Btn.setText("ISIT121");
                    }
                    else if(rs.getString("module2").equals("")){
                        
                        mod2Btn.setVisible(false);
                    }
                    
                    //Module 3
                    if(rs.getString("module3").equals("CSCI312") ){
                        
                        mod3Btn.setVisible(true);
                        mod3Btn.setText("CSCI312");
                    }
                    else if(rs.getString("module3").equals("CSCI331")){
                        
                        mod3Btn.setVisible(true);
                        mod3Btn.setText("CSCI331");
                    }
                    else if(rs.getString("module3").equals("CSCI368")){
                        
                        mod3Btn.setVisible(true);
                        mod3Btn.setText("CSCI368");
                    }
                    else if(rs.getString("module3").equals("ISIT121")){
                        
                        mod3Btn.setVisible(true);
                        mod3Btn.setText("ISIT121");
                    }
                    else if(rs.getString("module3").equals("")){
                        
                        mod3Btn.setVisible(false);
                    }
 
                    
                    mainPanel.setVisible(false);
                    //lecturerPanel.setVisible(true); 
                    lecModSelectPanel.setVisible(true);
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid username/password");
                }
            
             }catch(SQLException ex ){
            
                Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        //Student Account selected in ComboBox 
        else if(mainComboBox.getSelectedItem() == "Student"){
            
            try{
            ps = con.prepareStatement("SELECT `name`,`password`, `module1`, `module2`, `module3` FROM `studentdata` WHERE `name` = ? AND `password` = ?");
            ps.setString(1, usertmp);
            ps.setString(2, tmpPass);
            
            rs = ps.executeQuery();
            
            
                if(rs.next()){
                    
                    //Module 1
                    if(rs.getString("module1").equals("CSCI312") ){
                        
                        mod1Btn1.setVisible(true);
                        mod1Btn1.setText("CSCI312");
                    }
                    else if(rs.getString("module1").equals("CSCI331")){
                        
                        mod1Btn1.setVisible(true);
                        mod1Btn1.setText("CSCI331");
                    }
                    else if(rs.getString("module1").equals("CSCI368")){
                        
                        mod1Btn1.setVisible(true);
                        mod1Btn1.setText("CSCI368");
                    }
                    else if(rs.getString("module1").equals("ISIT121")){
                        
                        mod1Btn1.setVisible(true);
                        mod1Btn1.setText("ISIT121");
                    }
                    else if(rs.getString("module1").equals("")){
                        
                        mod1Btn1.setVisible(false);
                    }
                    //Module 2
                    if(rs.getString("module2").equals("CSCI312") ){
                        
                        mod2Btn1.setVisible(true);
                        mod2Btn1.setText("CSCI312");
                    }
                    else if(rs.getString("module2").equals("CSCI331")){
                        
                        mod2Btn1.setVisible(true);
                        mod2Btn1.setText("CSCI331");
                    }
                    else if(rs.getString("module2").equals("CSCI368")){
                        
                        mod2Btn1.setVisible(true);
                        mod2Btn1.setText("CSCI368");
                    }
                    else if(rs.getString("module2").equals("ISIT121")){
                        
                        mod2Btn1.setVisible(true);
                        mod2Btn1.setText("ISIT121");
                    }
                    else if(rs.getString("module2").equals("")){
                        
                        mod2Btn1.setVisible(false);
                    }
                    
                    //Module 3
                    if(rs.getString("module3").equals("CSCI312") ){
                        
                        mod3Btn1.setVisible(true);
                        mod3Btn1.setText("CSCI312");
                    }
                    else if(rs.getString("module3").equals("CSCI331")){
                        
                        mod3Btn1.setVisible(true);
                        mod3Btn1.setText("CSCI331");
                    }
                    else if(rs.getString("module3").equals("CSCI368")){
                        
                        mod3Btn1.setVisible(true);
                        mod3Btn1.setText("CSCI368");
                    }
                    else if(rs.getString("module3").equals("ISIT121")){
                        
                        mod3Btn1.setVisible(true);
                        mod3Btn1.setText("ISIT121");
                    }
                    else if(rs.getString("module3").equals("")){
                        
                        mod3Btn1.setVisible(false);
                    }
                
                    mainPanel.setVisible(false);
                    //studentPanel.setVisible(true); 
                    studModSelectPanel.setVisible(true);
                   
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid username/password");
                }
            
             }catch(SQLException ex ){
            
                Logger.getLogger(uw2lFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_mainloginBtnMouseClicked

    
    public boolean verifText(){
        
        if(managingUserTF.getText().equals("") || uownoUserTF.getText().equals("")
            || mod1UserTF.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "One or more empty field!");
            return false;
        }
        else{
            
            return true;
        }
    }
    
    private void editUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserBtnActionPerformed
        // TODO add your handling code here:
        
        String studName = managingUserTF.getText();
        String studUowno = uownoUserTF.getText();
        String studPassword = studName + studUowno;
        String tmpPass = getMD5(studPassword);
        String studMod1 = mod1UserTF.getText();
        String studMod2 = mod2UserTF.getText();
        String studMod3 = mod3UserTF.getText();
                
        if(verifText()){
            
            student std = new student();
            std.addUpdateDeleteStudent('u', studName, studUowno, tmpPass, studMod1, studMod2, studMod3);
            
            usersTable.setModel(new DefaultTableModel(null,new Object[]{"Name","UOWno","Module1","Module2","Module3"}));
            std.fillUserTable(usersTable, "");
        }
    }//GEN-LAST:event_editUserBtnActionPerformed

    private void adminPanelExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPanelExitBtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_adminPanelExitBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        
        mainPanel.setVisible(true);
        adminPanel.setVisible(false);
        usernameTF.setText("");
        passPF.setText("");
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void mainloginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainloginBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainloginBtnActionPerformed

    private void mainExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainExitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainExitBtnActionPerformed

    private void lecExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lecExitBtnMouseClicked
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_lecExitBtnMouseClicked

    private void studExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studExitBtnActionPerformed
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_studExitBtnActionPerformed

    private void studLogoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studLogoutBtnMouseClicked
        // TODO add your handling code here:
        
        mainPanel.setVisible(true);
        studentPanel.setVisible(false);
        usernameTF.setText("");
        passPF.setText("");
    }//GEN-LAST:event_studLogoutBtnMouseClicked

    private void studExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studExitBtnMouseClicked
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_studExitBtnMouseClicked

    private void addUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserBtnActionPerformed
        // TODO add your handling code here:
        
        String studName = managingUserTF.getText();
        String studUowno = uownoUserTF.getText();
        String studPassword = studName + studUowno;
        String tmpPass = getMD5(studPassword);
        String studMod1 = mod1UserTF.getText();
        String studMod2 = mod2UserTF.getText();
        String studMod3 = mod3UserTF.getText();
        
        if(verifText()){
            
            student std = new student();
            std.addUpdateDeleteStudent('i', studName, studUowno, tmpPass, studMod1, studMod2, studMod3);
            
            try{
                
                usersTable.setModel(new DefaultTableModel(null,new Object[]{"Name","UOWno","Module1","Module2","Module3"}));
                std.fillUserTable(usersTable, "");
                
            }catch(Exception ex){
                
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_addUserBtnActionPerformed

    int rowIndex;
    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
        // TODO add your handling code here:
        
        //geta new Model
        model = (DefaultTableModel)usersTable.getModel();
        
        rowIndex = usersTable.getSelectedRow();
        
        managingUserTF.setText(model.getValueAt(rowIndex, 0).toString());
        uownoUserTF.setText(model.getValueAt(rowIndex, 1).toString());
        mod1UserTF.setText(model.getValueAt(rowIndex, 2).toString());
        mod2UserTF.setText(model.getValueAt(rowIndex, 3).toString());
        mod3UserTF.setText(model.getValueAt(rowIndex, 4).toString());
    }//GEN-LAST:event_usersTableMouseClicked

    private void deleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserBtnActionPerformed
        // TODO add your handling code here:
        
        if(managingUserTF.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "No Student Selected!");
        }
        else{
            
            String name = String.valueOf(managingUserTF.getText());
            std.addUpdateDeleteStudent('d', name, null, null, null, null, null);
            std.fillUserTable(usersTable, "");
            usersTable.setModel(new DefaultTableModel(null,new Object[]{"Name","UOWno","Module1","Module2","Module3"}));
            std.fillUserTable(usersTable, "");
            
            managingUserTF.setText("");
            uownoUserTF.setText("");
            mod1UserTF.setText("");
            mod2UserTF.setText("");
            mod3UserTF.setText("");

        }
    }//GEN-LAST:event_deleteUserBtnActionPerformed

    private void usersTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usersTableKeyReleased
        // TODO add your handling code here:
        
        if(evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN){
            
            rowIndex = usersTable.getSelectedRow();
            
            managingUserTF.setText(model.getValueAt(rowIndex, 0).toString());
            uownoUserTF.setText(model.getValueAt(rowIndex, 1).toString());
            mod1UserTF.setText(model.getValueAt(rowIndex, 2).toString());
            mod2UserTF.setText(model.getValueAt(rowIndex, 3).toString());
            mod3UserTF.setText(model.getValueAt(rowIndex, 4).toString());
        }
    }//GEN-LAST:event_usersTableKeyReleased

    private void mod1BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod1BtnMouseClicked
        // TODO add your handling code here:
        
        lecModLabel.setText(mod1Btn.getText());
        lecModSelectPanel.setVisible(false);
        lecturerPanel.setVisible(true);
        
        
        //Module 1
        if(lecModLabel.getText().equals("CSCI312") ){
                        
            
            //File file1 = new File(getClass().getResource("/uw2l/312lec").getFile());
            File file1 = new File(getClass().getResource("C:/wamp64/www/312lecture").getFile());
            File[] files1 = file1.listFiles();
            mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod312.addRow(rows);
            }
            
      
        }
        else if(lecModLabel.getText().equals("CSCI331")){
                        
            File file1 = new File(getClass().getResource("/uw2l/331vids").getFile());
            File[] files1 = file1.listFiles();
            mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod331.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/uw2l/368vids").getFile());
            File[] files1 = file1.listFiles();
            mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod368.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/uw2l/121vids").getFile());
            File[] files1 = file1.listFiles();
            mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod121.addRow(rows);
            }
        }
            
        
    }//GEN-LAST:event_mod1BtnMouseClicked

    private void mod2BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod2BtnMouseClicked
        // TODO add your handling code here:
       
        
        lecModLabel.setText(mod2Btn.getText());
        lecModSelectPanel.setVisible(false);
        lecturerPanel.setVisible(true);
     
        
        //Module 1
        if(lecModLabel.getText().equals("CSCI312") ){
                        
            //DefaultListModel mod312L = new DefaultListModel();
            File file1 = new File(getClass().getResource("/uw2l/312lec").getFile());
            File[] files1 = file1.listFiles();
            mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod312.addRow(rows);
            }

        }
        else if(lecModLabel.getText().equals("CSCI331")){
                        
             File file1 = new File(getClass().getResource("/uw2l/331vids").getFile());
            File[] files1 = file1.listFiles();
            mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod331.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/uw2l/368vids").getFile());
            File[] files1 = file1.listFiles();
            mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod368.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/uw2l/121vids").getFile());
            File[] files1 = file1.listFiles();
            mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod2BtnMouseClicked

    private void mod3BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod3BtnMouseClicked
        // TODO add your handling code here:
        
        lecModLabel.setText(mod3Btn.getText());
        lecModSelectPanel.setVisible(false);
        lecturerPanel.setVisible(true);
        
        
        //Module 1
        if(lecModLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l/312lec").getFile());
            File[] files1 = file1.listFiles();
            mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod312.addRow(rows);
            }
   
        }
        else if(lecModLabel.getText().equals("CSCI331")){
                        
             File file1 = new File(getClass().getResource("/uw2l/331vids").getFile());
            File[] files1 = file1.listFiles();
            mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod331.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/uw2l/368vids").getFile());
            File[] files1 = file1.listFiles();
            mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod368.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/uw2l/121vids").getFile());
            File[] files1 = file1.listFiles();
            mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod3BtnMouseClicked

    private void mod4BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod4BtnMouseClicked
        // TODO add your handling code here:
        
        lecModLabel.setText(mod4Btn.getText());
        lecModSelectPanel.setVisible(false);
        lecturerPanel.setVisible(true);
        
        
        if(lecModLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l/312lec").getFile());
            File[] files1 = file1.listFiles();
            mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod312.addRow(rows);
            }

        }
        else if(lecModLabel.getText().equals("CSCI331")){
                        
             File file1 = new File(getClass().getResource("/uw2l/331vids").getFile());
            File[] files1 = file1.listFiles();
            mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod331.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/uw2l/368vids").getFile());
            File[] files1 = file1.listFiles();
            mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod368.addRow(rows);
            }
        }
        else if(lecModLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/uw2l/121vids").getFile());
            File[] files1 = file1.listFiles();
            mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
            mod121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                mod121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod4BtnMouseClicked

    private void lecLogoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lecLogoutBtnMouseClicked
        // TODO add your handling code here:
        
        mainPanel.setVisible(true);
        lecturerPanel.setVisible(false);
        usernameTF.setText("");
        passPF.setText("");
        DefaultTableModel model = (DefaultTableModel)lecPanLecVidTable.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_lecLogoutBtnMouseClicked

    private void mod1Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod1Btn1MouseClicked
        // TODO add your handling code here:
        
        studentPanelLabel.setText(mod1Btn1.getText());
        studModSelectPanel.setVisible(false);
        studentPanel.setVisible(true);
        
        
        if(studentPanelLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l.312lec").getFile());
            File[] files1 = file1.listFiles();
            stud312 = (DefaultTableModel)studentVidTable.getModel();
            stud312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud312.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI331")){
                        
            File file1 = new File(getClass().getResource("/331vids").getFile());
            File[] files1 = file1.listFiles();
            stud331 = (DefaultTableModel)studentVidTable.getModel();
            stud331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud331.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/368vids").getFile());
            File[] files1 = file1.listFiles();
            stud368 = (DefaultTableModel)studentVidTable.getModel();
            stud368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud368.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/121vids").getFile());
            File[] files1 = file1.listFiles();
            stud121 = (DefaultTableModel)studentVidTable.getModel();
            stud121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod1Btn1MouseClicked

    private void mod4Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod4Btn1MouseClicked
        // TODO add your handling code here:
        
        studentPanelLabel.setText(mod4Btn1.getText());
        studModSelectPanel.setVisible(false);
        studentPanel.setVisible(true);
        
        if(studentPanelLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l/312lec").getFile());
            File[] files1 = file1.listFiles();
            stud312 = (DefaultTableModel)studentVidTable.getModel();
            stud312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud312.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI331")){
                        
            File file1 = new File(getClass().getResource("/331vids").getFile());
            File[] files1 = file1.listFiles();
            stud331 = (DefaultTableModel)studentVidTable.getModel();
            stud331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud331.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/368vids").getFile());
            File[] files1 = file1.listFiles();
            stud368 = (DefaultTableModel)studentVidTable.getModel();
            stud368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud368.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/121vids").getFile());
            File[] files1 = file1.listFiles();
            stud121 = (DefaultTableModel)studentVidTable.getModel();
            stud121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod4Btn1MouseClicked

    private void mod2Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod2Btn1MouseClicked
        // TODO add your handling code here:
        
        studentPanelLabel.setText(mod2Btn1.getText());
        studModSelectPanel.setVisible(false);
        studentPanel.setVisible(true);
        
        if(studentPanelLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l.312lec").getFile());
            File[] files1 = file1.listFiles();
            stud312 = (DefaultTableModel)studentVidTable.getModel();
            stud312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud312.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI331")){
                        
            File file1 = new File(getClass().getResource("/331vids").getFile());
            File[] files1 = file1.listFiles();
            stud331 = (DefaultTableModel)studentVidTable.getModel();
            stud331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud331.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/368vids").getFile());
            File[] files1 = file1.listFiles();
            stud368 = (DefaultTableModel)studentVidTable.getModel();
            stud368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud368.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/121vids").getFile());
            File[] files1 = file1.listFiles();
            stud121 = (DefaultTableModel)studentVidTable.getModel();
            stud121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod2Btn1MouseClicked

    private void mod3Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mod3Btn1MouseClicked
        // TODO add your handling code here:
        
        studentPanelLabel.setText(mod3Btn1.getText());
        studModSelectPanel.setVisible(false);
        studentPanel.setVisible(true);
        
        if(studentPanelLabel.getText().equals("CSCI312") ){
                        
            File file1 = new File(getClass().getResource("/uw2l.312lec").getFile());
            File[] files1 = file1.listFiles();
            stud312 = (DefaultTableModel)studentVidTable.getModel();
            stud312.setColumnIdentifiers(new String[]{"312 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud312.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI331")){
                        
            File file1 = new File(getClass().getResource("/331vids").getFile());
            File[] files1 = file1.listFiles();
            stud331 = (DefaultTableModel)studentVidTable.getModel();
            stud331.setColumnIdentifiers(new String[]{"331 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud331.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("CSCI368")){
                        
            File file1 = new File(getClass().getResource("/368vids").getFile());
            File[] files1 = file1.listFiles();
            stud368 = (DefaultTableModel)studentVidTable.getModel();
            stud368.setColumnIdentifiers(new String[]{"368 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud368.addRow(rows);
            }
        }
        else if(studentPanelLabel.getText().equals("ISIT121")){
                        
            File file1 = new File(getClass().getResource("/121vids").getFile());
            File[] files1 = file1.listFiles();
            stud121 = (DefaultTableModel)studentVidTable.getModel();
            stud121.setColumnIdentifiers(new String[]{"121 Videos"});
            Object[] rows = new Object[1];
            for(int i = 0; i < files1.length; i++){
                
                rows[0] = files1[i].getName();
                stud121.addRow(rows);
            }
        }
    }//GEN-LAST:event_mod3Btn1MouseClicked

    public void loadVideo(String path){
        
       mediaPlayerComponent.mediaPlayer().media().startPaused(path);
    }
    
    private void watchVidBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watchVidBtnActionPerformed
        // TODO add your handling code here:
        
        
                
        if(lecModLabel.getText().equals("CSCI312")){
            
            mod312 = (DefaultTableModel)lecPanLecVidTable.getModel();
   
            rowIndex = lecPanLecVidTable.getSelectedRow();
        
            String name1 = mod312.getValueAt(rowIndex, 0).toString();
            
            
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch(Exception e){
                
                
            }
            
            //videoPlayer vp1 = new videoPlayer(name1);
            //vp1.setVisible(true);
            //vp1.pack();
            //vp1.setLocationRelativeTo(vp1);
            //vp1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //vp1.theTitle.setText(name1);
            //vp1.thelocate.setText(lecModLabel.getText());
            
           // vidTitleLabel.setText(name1);
            
            System.out.println(name1);
          
            playingVidPanel.setVisible(true);
            playingVidPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
            lecturerPanel.setVisible(false);
            lecVidPlayPanel.setVisible(true);
            uw2lFrame app1 = new uw2lFrame();
            String fullname = "C:\\Users\\harri\\OneDrive\\Documents\\NetBeansProjects\\uW2L\\src\\uw2l\\312lec\\"+(vidTitleLabel.getText());
            loadVideo(fullname);
            
        }
        else if(lecModLabel.getText().equals("CSCI331")){
            
            mod331 = (DefaultTableModel)lecPanLecVidTable.getModel();
   
            rowIndex = lecPanLecVidTable.getSelectedRow();
        
            String name1 = mod331.getValueAt(rowIndex, 0).toString();
        
            
            System.out.println(name1); 
            
        }
        else if(lecModLabel.getText().equals("CSCI368")){
            
            mod368 = (DefaultTableModel)lecPanLecVidTable.getModel();
   
            rowIndex = lecPanLecVidTable.getSelectedRow();
        
            String name1 = mod368.getValueAt(rowIndex, 0).toString();
        
            
            System.out.println(name1);
        }
        else if(lecModLabel.getText().equals("ISIT121")){
            
            mod121 = (DefaultTableModel)lecPanLecVidTable.getModel();
   
            rowIndex = lecPanLecVidTable.getSelectedRow();
        
            String name1 = mod121.getValueAt(rowIndex, 0).toString();
        
            
            System.out.println(name1);
        }
         
    }//GEN-LAST:event_watchVidBtnActionPerformed

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        // TODO add your handling code here:
        
        mediaPlayerComponent.mediaPlayer().controls().play();
        mediaPlayerComponent.mediaPlayer().status().time();
        
          //String fullname = "/uw2l/312lec/"+(vidTitleLabel.getText());
          //loadVideo(fullname);
    }//GEN-LAST:event_playBtnActionPerformed

    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBtnActionPerformed
        // TODO add your handling code here:
        
        mediaPlayerComponent.mediaPlayer().controls().stop();
    }//GEN-LAST:event_stopBtnActionPerformed

    private void pauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseBtnActionPerformed
        // TODO add your handling code here:
        
        mediaPlayerComponent.mediaPlayer().controls().pause();
    }//GEN-LAST:event_pauseBtnActionPerformed

    private void rewindBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rewindBtnActionPerformed
        // TODO add your handling code here:
        
        mediaPlayerComponent.mediaPlayer().controls().skipTime(-14000);
    }//GEN-LAST:event_rewindBtnActionPerformed

    private void skipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipBtnActionPerformed
        // TODO add your handling code here:
        
        mediaPlayerComponent.mediaPlayer().controls().skipTime(4000);
    }//GEN-LAST:event_skipBtnActionPerformed


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
            java.util.logging.Logger.getLogger(uw2lFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(uw2lFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(uw2lFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(uw2lFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new uw2lFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserBtn;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JButton adminPanelExitBtn;
    private javax.swing.JButton deleteUserBtn;
    private javax.swing.JButton deleteVidBtn;
    private javax.swing.JButton dlVidBtn;
    private javax.swing.JButton editUserBtn;
    private javax.swing.JButton editVidBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton lecBackBtn;
    private javax.swing.JButton lecExitBtn;
    private javax.swing.JPanel lecLabelPanel;
    private javax.swing.JPanel lecLabelPanel1;
    private javax.swing.JButton lecLogoutBtn;
    private javax.swing.JLabel lecModLabel;
    private javax.swing.JPanel lecModSelectPanel;
    private javax.swing.JTable lecPanLecVidTable;
    private javax.swing.JPanel lecVidPlayPanel;
    private javax.swing.JPanel lecturerPanel;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JComboBox<String> mainComboBox;
    private javax.swing.JButton mainExitBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton mainloginBtn;
    private javax.swing.JLabel managerUsersLabel;
    private javax.swing.JTextField managingUserTF;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton mod1Btn;
    private javax.swing.JButton mod1Btn1;
    private javax.swing.JTextField mod1UserTF;
    private javax.swing.JButton mod2Btn;
    private javax.swing.JButton mod2Btn1;
    private javax.swing.JTextField mod2UserTF;
    private javax.swing.JButton mod3Btn;
    private javax.swing.JButton mod3Btn1;
    private javax.swing.JTextField mod3UserTF;
    private javax.swing.JButton mod4Btn;
    private javax.swing.JButton mod4Btn1;
    private javax.swing.JLabel module1Label;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passPF;
    private javax.swing.JButton pauseBtn;
    private javax.swing.JButton playBtn;
    private javax.swing.JPanel playingVidPanel;
    private javax.swing.JButton rewindBtn;
    private javax.swing.JLabel selectAccLabel;
    private javax.swing.JButton skipBtn;
    private javax.swing.JButton stopBtn;
    private javax.swing.JButton studDLVidBtn;
    private javax.swing.JButton studExitBtn;
    private javax.swing.JButton studLogoutBtn;
    private javax.swing.JPanel studModSelectPanel;
    private javax.swing.JButton studWatchVidBtn;
    private javax.swing.JButton studbackBtn;
    private javax.swing.JPanel studentPanel;
    private javax.swing.JLabel studentPanelLabel;
    private javax.swing.JTable studentVidTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel uownoLabel;
    private javax.swing.JTextField uownoUserTF;
    private javax.swing.JButton uploadVidBtn;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTF;
    private javax.swing.JTable usersTable;
    private javax.swing.JLabel vidTitleLabel;
    private javax.swing.JButton watchVidBtn;
    // End of variables declaration//GEN-END:variables
}
