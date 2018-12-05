/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Acesso {
    
    private int idAcesso;
    private String acesso;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public int getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(int idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }
    
    public void completaComboAcesso(JComboBox comboAcesso){
        String sql = "SELECT acesso FROM acesso";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String acesso = rs.getString("acesso");
                comboAcesso.addItem(acesso);
            }
            
        }catch(Exception e){// vai direto para catch
            System.out.println("Catch acesso atvado: " + e.getMessage());// apresenta null
        }
        
    }
    public String completaAcesso(){
        String sql = "SELECT * FROM acesso";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.first();
            while(rs.next()){
                String acesso = rs.getString("acesso");
                int id = rs.getInt("idacesso");
                String frase = "\nPara " + acesso + ", digite " + id;
                return frase;
            }
            
        }catch(Exception e){// vai direto para catch
            System.out.println("Catch acesso atvado: " + e.getMessage());// apresenta null
        }
        return  null;
    }
    public int idComboAcesso(String nome){
        String sql = "SELECT idacesso, acesso FROM acesso WHERE acesso = '" + nome + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            return rs.getInt("idacesso");
            
        }catch(Exception e){
            System.out.println("Catch atvado: " + e.getMessage());
            return 0;
        }        
    }
    
    public void listaAcessos(JTable tabela){
        String sql = "SELECT * FROM acesso";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
                DefaultTableModel acesso = new DefaultTableModel();
                tabela.setModel(acesso);
                
                acesso.addColumn("ID");
                acesso.addColumn("Acesso");
                
                tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
                
                while(rs.next()){
                    int idEvento = rs.getInt("idacesso");
                    String nomeEvento = rs.getString("acesso");
                    acesso.addRow(new Object[]{idEvento, nomeEvento});
                }

            
        }catch(Exception ex){
            System.out.println("Erro em tabela de acessos. Mensagem: " + ex.getMessage());
        }    
    }
    public void buscaAcessos(JTable tabela, JTextField campo){
        String sql = "SELECT * FROM acesso WHERE acesso LIKE '%" + campo.getText() + "%'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
                DefaultTableModel acesso = new DefaultTableModel();
                tabela.setModel(acesso);
                
                acesso.addColumn("ID");
                acesso.addColumn("Acesso");
                
                tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
                
                while(rs.next()){
                    int idEvento = rs.getInt("idacesso");
                    String nomeEvento = rs.getString("acesso");
                    acesso.addRow(new Object[]{idEvento, nomeEvento});
                }

            
        }catch(Exception ex){
            System.out.println("Erro em tabela de acessos. Mensagem: " + ex.getMessage());
        }    
    }
    
    
}
