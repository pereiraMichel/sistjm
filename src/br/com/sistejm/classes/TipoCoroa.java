/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Michel
 */
public class TipoCoroa {
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    public int idtipocoroa;
    public String nome;

    public int getIdtipocoroa() {
        return idtipocoroa;
    }

    public void setIdtipocoroa(int idtipocoroa) {
        this.idtipocoroa = idtipocoroa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int retornaIdTipoCoroa(){
        config = new Configuracoes();
        try{
            String sql = "SELECT idtipocoroa FROM tipocoroa WHERE nome = '" + this.nome + "'";
            
//            System.out.println(sql);
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idtipocoroa");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do tipo de Coroa. Erro: " + ex.getMessage(), "Tipo de Coroa", "sistejm.tipocoroa");
        }        
        return 0;
    }
    
    
}
