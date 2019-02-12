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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Exu {
    
    private int idExu;
    private String nome;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getIdExu() {
        return idExu;
    }

    public void setIdExu(int idExu) {
        this.idExu = idExu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void preencheTabExu(JTable tabela){

        String sql = "SELECT * FROM exu "
                + "   ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("Exu");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORTAB+ ex.getMessage(), "Tabela de Exu", "sistejm.tabexu");
        }        
    }
    public void buscaTabExus(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM exu WHERE nome LIKE '%" + texto.getText() + "' "
                + "   ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("Exu");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORTAB+ ex.getMessage(), "Tabela de Exu", "sistejm.tabBexu");
        }        
    }
    
    public void verificaDuplicidade(){
        config = new Configuracoes();

        String sql = "SELECT * FROM exu WHERE nome LIKE '%" + this.nome + "' "
                + "   ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                this.idExu = rs.getInt("idexu");
                alterarExu();
            }else{
                incluirExu();
            }
        }catch(Exception ex){
            config.gravaErroLog("Erro na consulta de duplicidade de exu. Descrição: " + ex.getMessage(), "Verificação de Exu", "sistejm.verexu");
        }        
    }
    
    public boolean verificaExistente(){
        config = new Configuracoes();

        String sql = "SELECT * FROM exu WHERE nome = '" + this.nome + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            config.gravaErroLog("Erro na consulta de duplicidade de exu. Descrição: " + ex.getMessage(), "Verificação de Exu", "sistejm.verexu");
        }
        return false;        
    }
    
    public boolean incluirExu(){
        con = new Conexao();
        config = new Configuracoes();
        
        this.idExu = con.ultimoId("exu", "idexu");
        
        String sql = "INSERT INTO exu (idexu, nome) "
                + "VALUES (" + this.idExu + ", '" + this.nome + "')";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORINC+ ex.getMessage(), "Inclusão de Exu", "sistejm.incexu");
        }
        return false;
    }
    
    public boolean alterarExu(){
        config = new Configuracoes();
        
        String sql = "UPDATE exu SET nome = '" + this.nome + "' WHERE idexu = " + this.idExu;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORALT+ ex.getMessage(), "Alteração de Exu", "sistejm.altexu");
        }
        return false;
    }
    public boolean excluirExu(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM exu WHERE idexu = " + this.idExu;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROREXC+ ex.getMessage(), "Exclusão de Exu", "sistejm.excexu");
        }
        return false;
    }
    
    public int retornaIdExu(){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM exu WHERE nome = '" + this.nome + "'";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idexu");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do de Exu. Erro: " + ex.getMessage(), "Exu", "sistejm.idexu");
        }        
        return 0;
    }
    
}
