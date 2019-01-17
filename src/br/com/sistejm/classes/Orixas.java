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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Orixas {
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config = new Configuracoes();
    
    private int idOrixa;
    private String orixa;

    public int getIdOrixa() {
        return idOrixa;
    }

    public void setIdOrixa(int idOrixa) {
        this.idOrixa = idOrixa;
    }

    public String getOrixa() {
        return orixa;
    }

    public void setOrixa(String orixa) {
        this.orixa = orixa;
    }
    
    public void preencheTabOrixas(JTable tabela){

        String sql = "SELECT * FROM orixas";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("Orixás");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de tabela geral de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.tipoorixas");
        }        
    }
    
    public void buscaTabOrixas(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM orixas WHERE nome LIKE '%" + texto.getText() + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("ID");
            orixa.addColumn("Orixás");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(110);

            while(rs.next()){
                int idEvento = rs.getInt("idorixa");
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de tabela (busca) de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.tipoorixas");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT nome FROM orixas WHERE nome = '" + this.orixa + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("SQL: " + sql);
            
            //rs.next();
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de verificação de duplicidade de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.tipoorixas");
        }
        return false;
    }
    
    public boolean incluirOrixa(){
        con = new Conexao();
        if(!this.verificaExistente()){
            
            this.idOrixa = con.ultimoId("orixas", "idorixa");

            String sql = "INSERT INTO orixas (idorixa, nome) "
                    + "VALUES (" + this.idOrixa+ ", '" + this.orixa + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.orixas");
            }
        }
        return false;
    }
    
    public boolean alterarOrixa(){
        String sql = "UPDATE orixas SET nome = '" + this.orixa + "' WHERE idorixa = " + this.idOrixa;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de alteração de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.orixas");
        }
        return false;
    }
    public boolean excluirOrixa(){
        String sql = "DELETE FROM orixas WHERE idorixa = " + this.idOrixa;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.orixas");
        }
        return false;
    }
    
    public void preencheComboOrixa(JComboBox combo){

        config = new Configuracoes();
        String nome = null;
        try{
            String sql = "SELECT nome FROM orixas ";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                nome = rs.getString("nome");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de preenchimento combo de Orixá. Erro: " + ex.getMessage(), "Orixá", "sistejm.orixas");
        }
    }
    
    public int retornaIdOrixa(){
        config = new Configuracoes();
        try{
            String sql = "SELECT * "
                    + "FROM orixas "
                    + "WHERE nome = '" + this.orixa + "'";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idorixa");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do de Orixá. Erro: " + ex.getMessage(), "Id Orixás", "sistejm.idorixa");
        }        
        return 0;
    }
    
}
