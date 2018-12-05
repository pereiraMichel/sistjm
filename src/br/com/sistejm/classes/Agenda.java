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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Agenda {
    
    private String data;
    private String atividade;
    private int idagenda;

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public int getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(int idagenda) {
        this.idagenda = idagenda;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
    
    public void preencheTabAgenda(JTable tabela){

        String sql = "SELECT * FROM agenda";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel agenda = new DefaultTableModel();
            tabela.setModel(agenda);

            agenda.addColumn("ID");
            agenda.addColumn("Data");
            agenda.addColumn("Atividade");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);

            while(rs.next()){
                int idAgenda = rs.getInt("idagenda");
                String data = rs.getString("data");
                String atividade = rs.getString("atividade");
                agenda.addRow(new Object[]{idAgenda, data, atividade});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agenda. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabAgenda(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM agenda WHERE atividade OR data LIKE '%" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel agenda = new DefaultTableModel();
            tabela.setModel(agenda);

            agenda.addColumn("ID");
            agenda.addColumn("Data");
            agenda.addColumn("Agenda");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);

            while(rs.next()){
                int idAgenda = rs.getInt("idagenda");
                String atividade = rs.getString("atividade");
                String dataAgenda = rs.getString("data");
                agenda.addRow(new Object[]{idAgenda, dataAgenda, atividade});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agenda. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT atividade FROM agenda WHERE atividade = '" + this.atividade + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            System.out.println("Catch verificação de duplicidade de agenda ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluirAgenda(){
        con = new Conexao();
        if(!this.verificaExistente()){
            
            this.idagenda = con.ultimoId("agenda", "idagenda");

            String sql = "INSERT INTO agenda (idagenda, data, atividade) "
                    + "VALUES (" + this.idagenda+ ", '" + this.data + "', '" + this.atividade + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                System.out.println("Catch inclusão de Agenda ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarAgenda(){
        String sql = "UPDATE agenda SET atividade = '" + this.atividade + "', "
                + "data = '" + this.data + "' WHERE idagenda = " + this.idagenda;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de Agenda ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirAgenda(){
        String sql = "DELETE FROM agenda WHERE idagenda = " + this.idagenda;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Agenda ativado. Erro: " + ex.getMessage());
        }
        return false;
    }

    
}
