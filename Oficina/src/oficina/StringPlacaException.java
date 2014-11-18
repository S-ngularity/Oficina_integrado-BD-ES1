/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

/**
 *
 * @author Rich
 */
public class StringPlacaException extends Exception{
    private String msgErro;
    
    public String getMsgErro(){
        
        return this.msgErro;
    }
    
    StringPlacaException(String msgErro){
        super(msgErro);
        this.msgErro = msgErro;
    }
    
    
}
