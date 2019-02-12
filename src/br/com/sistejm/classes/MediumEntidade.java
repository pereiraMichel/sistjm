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
public class MediumEntidade {
    
    private int idMediumEntidade;
    private int codEntidade;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    Entidade ent;  

    public int getIdMediumEntidade() {
        return idMediumEntidade;
    }

    public void setIdMediumEntidade(int idMediumEntidade) {
        this.idMediumEntidade = idMediumEntidade;
    }

    public int getCodEntidade() {
        return codEntidade;
    }

    public void setCodEntidade(int codEntidade) {
        this.codEntidade = codEntidade;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public void tabMediumEntidade(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT m.nome, e.nome AS entidade "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ent me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN entidade e ON e.identidade = me.cod_entidade ";
//                    + "WHERE m.idmedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Matrícula");
            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(110);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                medium.addRow(new Object[]{id, matricula, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exibir a tabela de entidade. Erro: " + ex.getMessage(), "Tabela de Entidade", "sistejm.entidade");
        }        
    }
    public void buscaTabMediumMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM mediuns WHERE matricula LIKE '%" + texto.getText() + "%'";

        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Matrícula");
            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(110);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                medium.addRow(new Object[]{id, matricula, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exibir a tabela de entidade. Erro: " + ex.getMessage(), "Tabela de Entidade", "sistejm.entidade");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT codMedium FROM medium_ent "
                + "WHERE codMedium = " + this.codMedium + " "
                + "AND cod_entidade = " + this.codEntidade;
        
        try{
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
            config.gravaErroLog("Tentativa de verificação de duplicidade da entidade do Médium. Erro: " + ex.getMessage(), "Duplicidade de Entidade", "sistejm.entidade");
        }
        JOptionPane.showMessageDialog(null, "Entidade existente.");
        return false;
    }
    
    public boolean incluirMediumEntidade(){
        con = new Conexao();
        config = new Configuracoes();
        
        if(!this.verificaExistente()){
            
            this.idMediumEntidade = con.ultimoId("medium_ent", "idmedium_ent");

            String sql = "INSERT INTO medium_ent (idmedium_ent, cod_entidade, codMedium) "
                    + "VALUES (" + this.idMediumEntidade + ", " + this.codEntidade + ", " + this.codMedium + ")";

//            System.out.println(sql);
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);
                
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão da entidade do Médium. Erro: " + ex.getMessage(), "Entidade", "sistejm.entidade");
            }
        }
        return false;
    }
    
    public boolean alterarMediumEntidade(){
        config = new Configuracoes();
        
        String sql = "UPDATE medium_ent SET cod_entidade = " + this.codEntidade + ""
                + " WHERE idmedium_ent = " + this.idMediumEntidade + " AND codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Tentativa de alteração da entidade do Médium. Erro: " + ex.getMessage(), "Alteração da Entidade", "sistejm.entidade");
        }
        return false;
    }
    public boolean excluirMediumEntidade(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM medium_ent WHERE idmedium_ent = " + this.idMediumEntidade;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão da entidade do Médium. Erro: " + ex.getMessage(), "Exclusão da Entidade", "sistejm.entidade");
        }
        return false;
    }
    public boolean excluirEntidadeMedium(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM medium_ent WHERE idmedium_ent = " + this.idMediumEntidade;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);

            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão da entidade do Médium. Erro: " + ex.getMessage(), "Exclusão da Entidade", "sistejm.entidade");
        }
        return false;
    }
    
    public int retornaIdMediumEntidade(){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM medium_ent "
                    + "WHERE codMedium = " + this.codMedium + " "
                    + "AND cod_entidade = " + this.codEntidade;
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idmedium_ent");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do Médium Orixá. Erro: " + ex.getMessage(), "Médium Orixá", "sistejm.mediumorixa");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.mediumorixa para mais informações");
//            System.out.println(" Erro: " + ex.getMessage());
        }        
        return 0;
    }
    public void exibeTabMediumEntPorId(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT e.nome AS nomeEntidade "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ent me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN entidade e ON e.identidade = me.cod_entidade "
                    + "WHERE m.idmedium = " + this.codMedium + " "
                    + "ORDER BY e.nome ASC";
        
//        System.out.println(sql);
        
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
                String nome = rs.getString("nomeEntidade");
                if(nome.equals("")){
                    medium.addRow(new Object[]{"Sem informações"});
                }else{
                    medium.addRow(new Object[]{nome});
                }
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de Entidades do Médium. Erro: " + ex.getMessage(), "Orixá", "sistejm.entidademedium");
        }            
    }
}
