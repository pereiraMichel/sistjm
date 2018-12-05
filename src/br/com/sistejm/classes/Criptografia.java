/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

//import com.sun.org.apache.xml.internal.security.utils.EncryptionConstants;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Michel
 */
public class Criptografia {
    
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String md5(){
        try{
            
            String sen = "";
            MessageDigest md = null;

        try{
            md = MessageDigest.getInstance("MD5");
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(this.senha.getBytes("UTF-8")));
        sen = hash.toString(16);
        return sen;
        }catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public void cripPassData(String pass){
       
    }
    
    public void descripPassData(String pass){
        
    }
    
}
