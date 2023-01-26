/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

public class lecturerList {
   
    private int id;
    private String name;
    private String password;
    private String module1;
    private String module2;
    private String module3;
    
    public lecturerList(int lecID, String lecName, String lecPass, String lecMod1, String lecMod2, String lecMod3)
    {
        this.id = lecID;
        this.name = lecName;
        this.password = lecPass;
        this.module1 = lecMod1;
        this.module2 = lecMod2;
        this.module3 = lecMod3;
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
