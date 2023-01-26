/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

public class adminList {
    
    private int id;
    private String name;
    private String password;
    private String module1;
    private String module2;
    private String module3;
    private String module4;
    
    
    public adminList(int aId, String aName, String aPass, String aMod1, String aMod2,String aMod3,String aMod4)
    {
        this.id = aId;
        this.name = aName;
        this.password = aPass;
        this.module1 = aMod1;
        this.module2 = aMod2;
        this.module3 = aMod3;
        this.module4 = aMod4;
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
    
    public String getMod4(){
        
        return module4;
    }
    
    public void setMod4(String module4){
        
        this.module4 = module4;
    }
}
