/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Michel
 */
public class Fotos {
    
    private int idFoto;
    private String foto;
    private int codMedium;

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    
    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public boolean incluirFoto(){
        con = new Conexao();
            
            this.idFoto = con.ultimoId("foto", "idfoto");

            String sql = "INSERT INTO foto (idfoto, foto, cod_medium) "
                    + "VALUES (" + this.idFoto + ", '" + this.foto + "', " + this.codMedium + ")";
            
//            System.out.println(sql);
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro na foto. Erro: " + ex.getMessage(), "Inclusão da Foto", "sistejm.incluifoto");
//                System.out.println("Catch inclusão de Médium ativado. Erro: " + ex.getMessage());
            }
        return false;
    }
    
    public boolean excluirFoto(){
        String sql = "DELETE FROM foto WHERE cod_medium = " + this.codMedium;
        
//            System.out.println(sql);
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na Foto. Erro: " + ex.getMessage(), "Exclusão da Foto", "sistejm.excluifoto");
//            System.out.println("Catch exclusão de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }

    public String retornaFoto(){
        config = new Configuracoes();
        con = new Conexao();
        String sql = "SELECT foto FROM foto WHERE cod_medium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("foto");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na foto. Erro: " + ex.getMessage(), "Retorno ID do Telefone", "sistejm.telefone");
        }
        return null;
    }


    
}
