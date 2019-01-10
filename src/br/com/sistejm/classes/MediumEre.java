/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class MediumEre {
    private int idMediumEre;
    private int codEre;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    Ere ere;
    

    public int getIdMediumEre() {
        return idMediumEre;
    }

    public void setIdMediumExu(int idMediumEre) {
        this.idMediumEre = idMediumEre;
    }

    public int getCodEre() {
        return codEre;
    }

    public void setCodEre(int codEre) {
        this.codEre = codEre;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public void tabMediumEre(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT me.idmedium_ere, m.nome, e.nome AS nomeEre "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ere me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN ere e ON e.idere = me.cod_ere ";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");
            medium.addColumn("Ere");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);

            while(rs.next()){
                int id = rs.getInt("idmedium_ere");
                String nome = rs.getString("nome");
                String matricula = rs.getString("nomeEre");
                medium.addRow(new Object[]{id, matricula, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exibir a tabela de Exu. Erro: " + ex.getMessage(), "Tabela de Exu", "sistejm.mexu");
        }        
    }
    public void buscaTabEreMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT me.idmedium_ere, m.nome, e.nome AS nomeEre "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ere me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN ere e ON e.idere = me.cod_ere "
                    + "WHERE matricula LIKE '%" + texto.getText() + "%'";

        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");
            medium.addColumn("Ere");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);

            while(rs.next()){
                int id = rs.getInt("me.idmedium_ere");
                String nome = rs.getString("m.nome");
                String exu = rs.getString("nomeEre");
                medium.addRow(new Object[]{id, nome, exu});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de buscar na tabela de Ere. Erro: " + ex.getMessage(), "Tabela de Erê", "sistejm.mere");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT codMedium FROM medium_ere "
                + "WHERE codMedium = " + this.codMedium + " "
                + "AND cod_ere = " + this.codEre;
        
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
            config.gravaErroLog("Tentativa de verificação de duplicidade da Erê do Médium. Erro: " + ex.getMessage(), "Duplicidade de Erê", "sistejm.mere");
        }
//        JOptionPane.showMessageDialog(null, "Caboclo existente.");
        return false;
    }
    
    public boolean incluirMediumEre(){
        con = new Conexao();
        config = new Configuracoes();
        
        if(!this.verificaExistente()){
            
            this.idMediumEre = con.ultimoId("medium_ere", "idmedium_ere");

            String sql = "INSERT INTO medium_exu (idmedium_ere, cod_ere, codMedium) "
                    + "VALUES (" + this.idMediumEre + ", " + this.codEre + ", " + this.codMedium + ")";
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão de Erê do Médium. Erro: " + ex.getMessage(), "Inclusão de Erê", "sistejm.mere");
//                System.out.println("Catch inclusão de Entidade do Médium ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarMediumEre(){
        config = new Configuracoes();
        
        String sql = "UPDATE medium_ere SET cod_ere = " + this.codEre + ""
                + " WHERE idmedium_ere = " + this.idMediumEre + " AND codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Tentativa de alteração do Erê do Médium. Erro: " + ex.getMessage(), "Alteração do Erê", "sistejm.mere");
        }
        return false;
    }
    public boolean excluirMediumEre(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM medium_exu WHERE codMedium = " + this.codMedium + " AND cod_ere = " + this.codEre;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão do Exu do Médium. Erro: " + ex.getMessage(), "Exclusão do Exu", "sistejm.mexu");
        }
        return false;
    }
    
    public void preencheTabMediumEre(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT DISTINCT m.nome FROM mediuns m "
                + "LEFT JOIN medium_exu me ON m.idmedium = mec.codMedium  "
                + "LEFT JOIN exu e ON e.idexu = me.cod_exu";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Nome");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                if(tabela.getRowCount() % 2 == 0){
                    tabela.setSelectionBackground(Color.WHITE);
                }
                medium.addRow(new Object[]{nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela geral do Médium | Exu. Erro: " + ex.getMessage(), "Exu", "sistejm.mexu");
        }            
    }

    public int retornaIdMediumEre(){
        config = new Configuracoes();
        try{
            String sql = "SELECT me.idmedium_ere, m.nome, e.nome AS ere FROM medium_ere me "
                    + "LEFT JOIN ere e ON e.idere = me.cod_ere "
                    + "LEFT JOIN mediuns m ON m.idmedium = me.codMedium "
                    + "WHERE me.codMedium = " + this.codMedium + " "
                    + "AND e.idere = " + this.codEre;
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idmedium_ere");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do Médium Ere. Erro: " + ex.getMessage(), "Médium Ere", "sistejm.mere");
        }        
        return 0;
    }
    
    public void exibeTabMediumErePorId(JTable tabela){
        config = new Configuracoes();
         String sql = "SELECT e.nome AS nomeEre "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ere me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN ere e ON e.idere = me.cod_ere "
                    + "WHERE m.idmedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Nome");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);

            while(rs.next()){
                String nome = rs.getString("nomeEre");
                if(nome.equals("")){
                    medium.addRow(new Object[]{"Sem informações"});
                }else{
                    medium.addRow(new Object[]{nome});
                }
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de Ere do Médium. Erro: " + ex.getMessage(), "Tabela de Erê", "sistejm.eremedium");
        }            
    }
    
    
}
