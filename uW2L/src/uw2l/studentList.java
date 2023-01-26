/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class studentList {
    
    private int id;
    private String name;
    private String uowno;
    private String password;
    private String module1;
    private String module2;
    private String module3;
    
    public studentList(int studentID, String studentName, String studentUOWno, String studentPass, String studentMod1, String studentMod2, String studentMod3)
    {
        this.id = studentID;
        this.name = studentName;
        this.uowno = studentUOWno;
        this.password = studentPass;
        this.module1 = studentMod1;
        this.module2 = studentMod2;
        this.module3 = studentMod3;
    }
    
    public int getId(){
        
        return id;
    }
    
    public void setId(int id){
        
        this.id = id;
    }
    
    public String getName(){
        
        return name;
    }
    
    public void setName(String name){
        
        this.name = name;
    }
    
    public String getUOWno(){
        
        return uowno;
    }
    
    public void setUOWno(String uowno){
        
        this.uowno = uowno;
    }
    
    public String getPass(){
        
        return password;
    }
    
    public void setPass(String password){
        
        this.password = password;
    }
    
    public String getMod1(){
        
        return module1;
    }
    
    public void setMod1(String module1){
        
        this.module1 = module1;
    }
    
    public String getMod2(){
        
        return module2;
    }
    
    public void setMod2(String module2){
        
        this.module2 = module2;
    }
    
    public String getMod3(){
        
        return module3;
    }
    
    public void setMod3(String module3){
        
        this.module3 = module3;
    }
    
     
}
