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

        String sql = "SELECT * FROM exu";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

//            orixa.addColumn("ID");
            orixa.addColumn("Exu");

//            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
//                int idEvento = rs.getInt("idexu");
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
//                orixa.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de exu. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabExus(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM exu WHERE nome LIKE '%" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("ID");
            orixa.addColumn("Exu");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(110);

            while(rs.next()){
                int idEvento = rs.getInt("idexu");
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de exu. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean incluirExu(){
        con = new Conexao();
        this.idExu = con.ultimoId("exu", "idexu");
        
        String sql = "INSERT INTO exu (idexu, nome) "
                + "VALUES (" + this.idExu + ", '" + this.nome + "')";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch inclusão de entidade ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean alterarExu(){
        String sql = "UPDATE exu SET nome = '" + this.nome + "' WHERE idexu = " + this.idExu;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de exu ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirExu(){
        String sql = "DELETE FROM exu WHERE idexu = " + this.idExu;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de entidade ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public int retornaIdExu(String nome){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM exu WHERE nome = '" + nome + "'";
            
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
