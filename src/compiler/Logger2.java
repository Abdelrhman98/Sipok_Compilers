/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author Islam
 */
public class Logger2 {
    public String log = "";
    
    public void add(String st){
        this.log += st + "\n";
    }
    
    public void clear(){
        this.log = "";
    }
    
    public String getText(){
        return this.log;
    }
}
