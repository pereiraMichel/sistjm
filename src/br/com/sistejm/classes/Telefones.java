/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Telefones {
    private int idTelefone;
    private String numTelefone;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumTelefone() {
        return numTelefone;
    }

    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public void preencheTabTelefones(JTable tabela){

        String sql = "SELECT * FROM telefones t " +
                      "WHERE codMedium = " + this.codMedium;
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Telefones");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            while(rs.next()){
                String telefone = rs.getString("numTelefone");
                medium.addRow(new Object[]{telefone});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de telefones. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaTelefone(){
        con = new Conexao();
        config = new Configuracoes();
            
            String sql = "SELECT * FROM telefones WHERE numTelefone = '" + this.numTelefone + " "
                    + "AND codMedium = " + this.codMedium;
            
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                
                rs.next();
                
                if(rs.absolute(1)){
                    return true;
                }

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro no telefone. Erro: " + ex.getMessage(), "Duplicidade do Telefone", "sistejm.duplictelefone");
            }
        return false;
    }
    public boolean incluirTelefone(){
        con = new Conexao();
            
            this.idTelefone = con.ultimoId("telefones", "idtelefone");

            String sql = "INSERT INTO telefones (idtelefone, numTelefone, codMedium) "
                    + "VALUES (" + this.idTelefone + ", '" + this.numTelefone + "', " + this.codMedium + ")";
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro no telefone. Erro: " + ex.getMessage(), "Inclusão do Telefone", "sistejm.incluitelefone");
//                System.out.println("Catch inclusão de Médium ativado. Erro: " + ex.getMessage());
            }
        return false;
    }
    
    public boolean excluirTelefone(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM telefones WHERE codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no telefone. Erro: " + ex.getMessage(), "Exclusão do Telefone", "sistejm.excluitelefone");
//            System.out.println("Catch exclusão de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }

    public int retornaIdTelefone(String telefone){
        config = new Configuracoes();
        con = new Conexao();
        String sql = "SELECT * FROM telefones WHERE telefone = '" + telefone + " "
                + "AND codMedium = " + this.codMedium;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idtelefone");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no telefone. Erro: " + ex.getMessage(), "Retorno ID do Telefone", "sistejm.telefone");
        }
        return 0;
    }
    
}
