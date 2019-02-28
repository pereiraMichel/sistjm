/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Batismo {
    private int idBatismo;
    private String padrinho;
    private String madrinha;
    private String dataBatismo;
    private int codMedium;

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;


    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }

    public String getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(String dataBatismo) {
        config = new Configuracoes();
        String formatoSQL = config.retornaFormatoDataSQL(dataBatismo);

        this.dataBatismo = formatoSQL;
    }

    public String getPadrinho() {
        return padrinho;
    }

    public void setPadrinho(String padrinho) {
        this.padrinho = padrinho;
    }

    public String getMadrinha() {
        return madrinha;
    }

    public void setMadrinha(String madrinha) {
        this.madrinha = madrinha;
    }
    
    public void preencheTabBatismoMedium(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT * FROM mediuns m"
                + "INNER JOIN batismo b ON b.codmedium = m.idmedium";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{id, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_TBATISMO + ex.getMessage() + ". SQL: " + sql, "Tabela de Batismo", "sistejm.tabbatismo");
        }        
    }
    public void buscaTabMedium(JTable tabela, JTextField texto){
        config = new Configuracoes();
        String sql = "SELECT * FROM mediuns m"
                + "INNER JOIN coroacao d ON d.codmedium = m.idmedium"
                + " WHERE m.nome LIKE '%" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{id, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_TBATISMO + ex.getMessage() + ". SQL: " + sql, "Busca na Tabela de Batismo", "sistejm.tabbatismo");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        config = new Configuracoes();
        String sql = "SELECT * FROM batismo WHERE codmedium = " + this.codMedium;
        
//        System.out.println(sql);

        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                alterarBatismoMedium();
            }else{
                incluirBatismoMedium();
            }
            
            return true;
           
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_VBATISMO + ex.getMessage() + ". SQL: " + sql, "Verificação de Batismo", "sistejm.verbatismo");
        }
        return false;
    }
    
    public boolean incluirBatismoMedium(){
        con = new Conexao();
        config = new Configuracoes();

            this.idBatismo = con.ultimoId("batismo", "idbatismo");

            String sql = "INSERT INTO batismo (idbatismo, padrinho, madrinha, dataBatismo, codmedium) "
                    + "VALUES (" + this.idBatismo+ ", '', '', "
                    + "'1901-01-01', " + this.codMedium + ")";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog(Constances.ERROR_IBATISMO + ex.getMessage() + ". SQL: " + sql, "Inclusão de Batismo", "sistejm.incluibatismo");
            }
        return false;
    }
    
    public boolean alterarBatismoMedium(){
        config = new Configuracoes();
        
        String sql = "UPDATE batismo SET "
                + "padrinho = '" + this.padrinho + "', "
                + "madrinha = '" + this.madrinha + "', "
                + "dataBatismo = '" + this.dataBatismo + "' "
                + " WHERE codmedium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println(Constances.ERROR_ABATISMO + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluiPadrinhosBatismoMedium(){
        config = new Configuracoes();
        String sql = "UPDATE batismo SET "
                + "padrinho = '" + this.padrinho + "', "
                + "madrinha = '" + this.madrinha + "' "
                + " WHERE codmedium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println(Constances.ERROR_ABATISMO + ex.getMessage());
        }
        return false;
    }
    public boolean incluiPadrinho(){
        config = new Configuracoes();
        String sql = "UPDATE batismo SET "
                + "padrinho = '" + this.padrinho + "' "
                + " WHERE codmedium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println(Constances.ERROR_ABATISMO + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluiMadrinha(){
        config = new Configuracoes();
        String sql = "UPDATE batismo SET "
                + "madrinha = '" + this.madrinha + "' "
                + " WHERE codmedium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println(Constances.ERROR_ABATISMO + ex.getMessage());
        }
        return false;
    }
    
    public boolean excluirBatismoMedium(){
        config = new Configuracoes();
        String sql = "DELETE FROM batismo WHERE codmedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_EBATISMO + ex.getMessage() + ". SQL: " + sql, "Exclusão de Batismo", "sistejm.excluibatismo");
        }
        return false;
    }
    
    public void consultaBatismoMedium(JTextField padrinho, JTextField madrinha, JTextField dataBatismo){
        config = new Configuracoes();

        String sql = "SELECT *, DATE_FORMAT(dataBatismo, '%d/%m/%Y') AS dataFBatismo "
                + "FROM batismo WHERE codmedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                
                String pad = rs.getString("padrinho");
                String mad = rs.getString("madrinha");
                String datBat = rs.getString("dataFBatismo");
                
                if(pad.equals(null)){
                    padrinho.setText("");
                }else{
                    padrinho.setText(pad);
                }
                if(mad.equals(null)){
                    madrinha.setText("");
                }else{
                    madrinha.setText(mad);
                }
                
                if(dataBatismo.equals("01/01/1901")){
                    dataBatismo.setText("");
                }else{
                    dataBatismo.setText(rs.getString("dataFBatismo"));
                }
            }
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_VBATISMO + ex.getMessage() + ". SQL: " + sql, "Verificação de Batismo", "sistejm.excluibatismo");
        }
    }

}
