/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Michel
 */
public class MediumOrixa {
    
    private int idMediumOrixa;
    private int codOrixa;
    private int codMedium;
    private int codTipo;
    private int tipoOrixa;// cabeça, juntó
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(int codTipo) {
        this.codTipo = codTipo;
    }
    
    public int getIdMediumOrixa() {
        return idMediumOrixa;
    }

    public void setIdMediumOrixa(int idMediumOrixa) {
        this.idMediumOrixa = idMediumOrixa;
    }

    public int getCodOrixa() {
        return codOrixa;
    }

    public void setCodOrixa(int codOrixa) {
        this.codOrixa = codOrixa;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }

    public int getTipoOrixa() {
        return tipoOrixa;
    }

    public void setTipoOrixa(int tipoOrixa) {
        this.tipoOrixa = tipoOrixa;
    }
    
    public void buscaTabMediumOrixa(JTable tabela){

        String sql = "SELECT o.nome AS nomeOrixa, tpo.tipo AS tipoOrixa "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_ori mo ON mo.codMedium = m.idmedium "
                    + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa "
                    + "LEFT JOIN tipo_orixa tpo ON tpo.idtipo_orixa = mo.codTipo "
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
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabMediumMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM mediuns WHERE matricula LIKE '%" + texto.getText() + "%'";
        
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
            System.out.println("Erro na busca de tabela de Médiuns. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT codMedium FROM medium_ori "
                + "WHERE codMedium = " + this.codMedium + " "
                + "AND codTipo = " + this.codTipo + " "
                + "AND cod_orixa = " + this.codOrixa;
        
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
            System.out.println("Catch verificação de duplicidade de Orixás de Médiuns ativado. Erro: " + ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Orixá existente.");
        return false;
    }
    
    public boolean incluirMediumOrixa(){
        con = new Conexao();
        if(!this.verificaExistente()){
            
            this.idMediumOrixa = con.ultimoId("medium_ori", "idmedium_ori");

            String sql = "INSERT INTO medium_ori (idmedium_ori, cod_orixa, codTipo, codMedium) "
                    + "VALUES (" + this.idMediumOrixa + ", " + this.codOrixa + ", " + this.codTipo + ", "
                    + " " + this.codMedium + ")";
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                System.out.println("Catch inclusão de Orixá do Médium ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarMediumOrixa(){
        String sql = "UPDATE medium_ori SET cod_orixa = '" + this.codOrixa + "', "
                + "codTipo = " + this.codTipo + ", "
                + " WHERE medium = " + this.idMediumOrixa + "AND codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de Orixás de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirMediumOrixa(){
        String sql = "DELETE FROM medium_ori WHERE codMedium = " + this.codMedium + " AND cod_orixa = " + this.codOrixa;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Orixá de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirOrixaMedium(){
        String sql = "DELETE FROM medium_ori WHERE codMedium = " + this.codMedium;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Orixá de Médium ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public void preencheComboMediumOrixa(JComboBox combo){

        String nome = null;
        try{
            String sql = "SELECT o.nome FROM mediuns m "
                    + " INNER JOIN medium_ori mo ON mo.codMedium = m.idmedium "
                    + " INNER JOIN orixas o ON o.idorixa = mo.codMedium";
            
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
    
    public void preencheTabMediumOri(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT DISTINCT m.nome FROM mediuns m "
                + "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  "
                + "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo "
                + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa";
//        String sql = "SELECT m.nome, o.nome, tp.tipo, tp.idtipo_orixa FROM mediuns m "
//                + "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  "
//                + "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo "
//                + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa";
        
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
            config.gravaErroLog("Tentativa de preenchimento da tabela geral do Médium | Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
        }            
    }

    public void preencheTabMediumOriPorNome(JTable tabela, String nome){
        config = new Configuracoes();
        String sql = "SELECT m.nome AS nomeMedium, o.nome AS nomeOrixa, tp.tipo, tp.idtipo_orixa FROM mediuns m "
                + "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  "
                + "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo "
                + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa "
                + "WHERE m.nome = '" + nome + "' "
                + "ORDER BY tp.idtipo_orixa";

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
         
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Orixá");
            medium.addColumn("Tipo");
 
            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);



            while(rs.next()){
                int id = rs.getInt("tp.idtipo_orixa");
                String orixa = rs.getString("nomeOrixa");
                String tipo = rs.getString("tp.tipo");
               
                medium.addRow(new Object[]{id, orixa, tipo });
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da busca do Médium | Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
        }  
        
    }

    public int retornaIdMediumOrixa(String nome){
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
    

    public void retornaOriMaePai(JLabel mae, JLabel pai){
        
        config = new Configuracoes();
        try{
            String sql = "SELECT m.nome, o.nome AS nomeOrixa, tp.tipo, tp.idtipo_orixa " +
            "FROM mediuns m " +
            "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  " +
            "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo " +
            "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa " +
            "WHERE m.idmedium = " + this.codMedium + " " +
            "ORDER BY tp.idtipo_orixa";
            
//            System.out.println(sql);
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.getInt("tp.idtipo_orixa") == 1){
                String getpai = rs.getString("nomeOrixa");
                pai.setText(getpai.toUpperCase());
            }
            rs.next();

            if(rs.getInt("tp.idtipo_orixa") == 2){
                String getmae = rs.getString("nomeOrixa");
                mae.setText(getmae.toUpperCase());
            }

//            while(rs.next()){
//                if(rs.getInt("tp.idtipo_orixa") == 1){
//                    pai.setText(rs.getString("nomeOrixa"));
//                }else{
//                    pai.setText("Orixá pai não cadastrado");
//                }
//                if(rs.getInt("tp.idtipo_orixa") == 2){
//                    mae.setText(rs.getString("nomeOrixa"));
//                }else{
//                    mae.setText("Orixá mãe não cadastrado");
//                }
//            }

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do de Orixá. Erro: " + ex.getMessage(), "Id Orixás", "sistejm.idorixa");
        }
        
    }    
    
}
