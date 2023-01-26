/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author harri
 */
public class student {
    
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
     
    public void addUpdateDeleteStudent(char opt,String studName, String studUowno, String studPassword, String studModule1, String studModule2, String studModule3){
        
        Connection con = getConnection();
        PreparedStatement ps;
        
        //insert
        if(opt == 'i'){
            
            try{
                
                ps = con.prepareStatement("INSERT INTO studentdata(name, uowno, password, module1, module2, module3) VALUES (?,?,?,?,?,?)");
                ps.setString(1, studName);
                ps.setString(2, studUowno);
                ps.setString(3, studPassword);
                ps.setString(4, studModule1);
                ps.setString(5, studModule2);
                ps.setString(6, studModule3);
                
                if(ps.executeUpdate() > 0){
                    
                    JOptionPane.showMessageDialog(null, "New Student Added");
                }
                
            }catch(SQLException ex){
                
                Logger.getLogger(studentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Update
        if(opt == 'u'){
            
            try{
                
                ps = con.prepareStatement("UPDATE `studentdata` SET `name` = ?, `uowno` = ?, `password` = ?,`module1` = ?, `module2` = ?, `module3` = ? WHERE `name` = ?");
                ps.setString(1, studName);
                ps.setString(2, studUowno);
                ps.setString(3, studPassword);
                ps.setString(4, studModule1);
                ps.setString(5, studModule2);
                ps.setString(6, studModule3);
                ps.setString(7, studName);
                
                if(ps.executeUpdate() > 0){
                    
                    JOptionPane.showMessageDialog(null, "Student Data Updated");
                }
                
            }catch(SQLException ex){
                
                Logger.getLogger(studentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Delete
        if(opt == 'd'){
            
            int YesOrNo = JOptionPane.showConfirmDialog(null, "The User will be deleted","User deleted",JOptionPane.OK_CANCEL_OPTION,0);
            
            if(YesOrNo == JOptionPane.OK_OPTION){
                
                try{
                    ps = con.prepareStatement("DELETE FROM `studentdata` WHERE `name` = ?");
                    ps.setString(1, studName);
                    
                    if(ps.executeUpdate() > 0){
                        
                        JOptionPane.showMessageDialog(null, "Student Deleted");
                    }
                }catch(SQLException ex){
                    
                    Logger.getLogger(studentList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public void fillUserTable(JTable table, String valueToSearch){
        
        Connection con = getConnection();
        PreparedStatement ps;
        
        try{
            ps = con.prepareStatement("SELECT `name`,`uowno`,`module1`,`module2`,`module3` FROM `studentdata` WHERE CONCAT(`name`,`uowno`,`module1`,`module2`,`module3`) LIKE ?");
            ps.setString(1, "%" +valueToSearch + "%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                
                row = new Object[5];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                model.addRow(row);
            }
            
        }catch(SQLException ex){
            
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
