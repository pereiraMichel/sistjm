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
public class MediumCaboclo {
    private int idMediumCaboclo;
    private int codCaboclo;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    Entidade ent;  

    public int getIdMediumCaboclo() {
        return idMediumCaboclo;
    }

    public void setIdMediumCaboclo(int idMediumCaboclo) {
        this.idMediumCaboclo = idMediumCaboclo;
    }

    public int getCodCaboclo() {
        return codCaboclo;
    }

    public void setCodCaboclo(int codCaboclo) {
        this.codCaboclo = codCaboclo;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public void tabMediumCaboclo(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT e.nome "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_caboclo mc ON mc.codMedium = m.idmedium "
                    + "LEFT JOIN caboclo c ON c.idcaboclo = mc.cod_caboclo "
                    + "WHERE m.idmedium = " + this.codMedium;
        
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
            config.gravaErroLog("Tentativa de exibir a tabela de caboclo. Erro: " + ex.getMessage(), "Tabela de Caboclo", "sistejm.mcaboclo");
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
            config.gravaErroLog("Tentativa de exibir a tabela de caboclo. Erro: " + ex.getMessage(), "Tabela de Caboclo", "sistejm.mcaboclo");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT codMedium FROM medium_caboclo "
                + "WHERE codMedium = " + this.codMedium + " "
                + "AND cod_caboclo = " + this.codCaboclo;
        
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
            config.gravaErroLog("Tentativa de verificação de duplicidade da caboclo do Médium. Erro: " + ex.getMessage(), "Duplicidade de Caboclo", "sistejm.mcaboclo");
        }
//        JOptionPane.showMessageDialog(null, "Caboclo existente.");
        return false;
    }
    
    public boolean incluirMediumCaboclo(){
        con = new Conexao();
        config = new Configuracoes();
        
        if(!this.verificaExistente()){
            
            this.idMediumCaboclo = con.ultimoId("medium_caboclo", "idmedium_caboclo");

            String sql = "INSERT INTO medium_caboclo (idmedium_caboclo, cod_caboclo, codMedium) "
                    + "VALUES (" + this.idMediumCaboclo + ", " + this.codCaboclo + ", " + this.codMedium + ", "
                    + " " + this.codMedium + ")";
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão do caboclo do Médium. Erro: " + ex.getMessage(), "Caboclo", "sistejm.mcaboclo");
//                System.out.println("Catch inclusão de Entidade do Médium ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarMediumCaboclo(){
        config = new Configuracoes();
        
        String sql = "UPDATE medium_caboclo SET cod_caboclo = " + this.codCaboclo + ""
                + " WHERE idmedium_caboclo = " + this.idMediumCaboclo + " AND codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Tentativa de alteração da Caboclo do Médium. Erro: " + ex.getMessage(), "Alteração do Caboclo", "sistejm.mcaboclo");
        }
        return false;
    }
    public boolean excluirMediumCaboclo(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM medium_caboclo WHERE codMedium = " + this.codMedium + " AND cod_caboclo = " + this.codCaboclo;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão do caboclo do Médium. Erro: " + ex.getMessage(), "Exclusão do Caboclo", "sistejm.mcaboclo");
//            System.out.println("Catch exclusão de Orixá de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public void preencheComboMediumCaboclo(JComboBox combo){

        String nome = null;
        try{
            String sql = "SELECT o.nome FROM mediuns m "
                    + " INNER JOIN medium_caboclo mc ON mc.codMedium = m.idmedium "
                    + " INNER JOIN caboclo c ON c.idcaboclo = mc.cod_caboclo";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                nome = rs.getString("o.nome");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            System.out.println(" Erro: " + ex.getMessage());
        }
    }
    
    public void preencheTabMediumCaboclo(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT DISTINCT m.nome FROM mediuns m "
                + "LEFT JOIN medium_caboclo mc ON m.idmedium = mc.codMedium  "
                + "LEFT JOIN caboclo c ON c.idcaboclo = mc.cod_caboclo";
        
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
            config.gravaErroLog("Tentativa de preenchimento da tabela geral do Médium | Caboclo. Erro: " + ex.getMessage(), "Caboclo", "sistejm.mcaboclo");
        }            
    }
    public void preencheTabMediumCabPorNome(JTable tabela, String nome){
        config = new Configuracoes();
        String sql = "SELECT m.nome AS nomeMedium, o.nome AS nomeCaboclo, mc.idmedium_caboclo "
                + "FROM mediuns m "
                + "LEFT JOIN medium_caboclo mc ON m.idmedium = mc.codMedium  "
                + "LEFT JOIN caboclo c ON c.idcaboclo = mc.cod_caboclo "
                + "WHERE m.nome = '" + nome + "' "
                + "ORDER BY c.idcaboclo";

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
         
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Caboclo");
 
            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(80);



            while(rs.next()){
                int id = rs.getInt("mc.idmedium_caboclo");
                String caboclo = rs.getString("nomeCaboclo");
               
                medium.addRow(new Object[]{id, caboclo});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da busca do Médium | Caboclo. Erro: " + ex.getMessage(), "Caboclo", "sistejm.mcaboclo");
        }  
        
    }

    public int retornaIdMediumCaboclo(String nome){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM tipo_orixa WHERE tipo = '" + nome + "'";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idtipo_orixa");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do Médium Orixá. Erro: " + ex.getMessage(), "Médium Orixá", "sistejm.mediumorixa");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.mediumorixa para mais informações");
//            System.out.println(" Erro: " + ex.getMessage());
        }        
        return 0;
    }    
}
