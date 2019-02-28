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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class TrabalhoSaidas {
    
    private int idtrabalho;
    private int quantidade;
    private int codproduto;
    private int codcoroa;
    private int codMedium;
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }

    public int getIdtrabalho() {
        return idtrabalho;
    }

    public void setIdtrabalho(int idtrabalho) {
        this.idtrabalho = idtrabalho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto = codproduto;
    }

    public int getCodcoroa() {
        return codcoroa;
    }

    public void setCodcoroa(int codcoroa) {
        this.codcoroa = codcoroa;
    }

    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT * "
                + "FROM trabsaidas "
                + "WHERE "
                + "codcoroa = " + this.codcoroa
                + " AND codproduto = " + this.codproduto
                + " AND quantidade = " + this.quantidade;
//                + " AND codmedium = " + this.codMedium;
        
//        System.out.println("SQL Verif Existente: " + sql);
        
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
            config = new Configuracoes();
            config.gravaErroLog("Houve um erro na verificação de duplicidade do trabalho de médiuns. Verifique sob o erro " + ex.getMessage(), "Verificação do trabalho de Médiuns", "sistejm.veriftrabsaidas");
        }
        return false;
        
    }
    public boolean incluirTrabalhoSaidas(){
        con = new Conexao();
        config = new Configuracoes();
        
//        if(!this.verificaExistente()){
            
            this.idtrabalho = con.ultimoId("trabsaidas", "idtrabalho");

            String sql = "INSERT INTO trabsaidas (idtrabalho, quantidade, codproduto, codcoroa) "
                    + "VALUES "
                    + "(" + this.idtrabalho + ", " + this.quantidade + ", " + this.codproduto + ", "
                    + "" + this.codcoroa + ")"; //, " + this.codMedium + "
            
//            System.out.println(sql);

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro na inclusão do trabalho de saídas. Verifique sob o erro " + ex.getMessage(), "Inclusão do trabalho de saídas", "sistejm.inctrabsaida");
            }
//        }else{
//            JOptionPane.showMessageDialog(null, "Trabalho já existente.");
//        }
        return false;
    }
    
    public boolean alterarTrabalho(){
        config = new Configuracoes();
    
        String sql = "UPDATE agendamento SET "
                + "quantidade = " + this.quantidade + ", "
                + "codproduto = " + this.codproduto + ", "
                + "codcoroa = " + this.codcoroa + ", "
                + " WHERE idtrabalho = " + this.idtrabalho;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na alteração do trabalho de saídas. Verifique sob o erro " + 
                    ex.getMessage(), "Alteração do trabalho de saídas", "sistejm.altrabatend");
        }
        return false;
    }

    public boolean excluirTrabalho(){
        config = new Configuracoes();
        String sql = "DELETE FROM trabsaidas WHERE idtrabalho = " + this.idtrabalho;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na exclusão do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Exclusão do trabalho de atendimento", "sistejm.excrabatend");
        }
        return false;
    }

    public void exibeTrabalhos(JTable tabela){
        config = new Configuracoes();
        String compCoroa = null;
        
        if(this.codcoroa == 0){
            compCoroa = " ";
        }else{
            compCoroa = "WHERE t.codcoroa = " + this.codcoroa + " ";
        }
        
        String sql = "SELECT tp.nome, p.produto, t.quantidade "
                   + "FROM trabsaidas t "
                   + "LEFT JOIN produtos p ON p.idproduto = t.codproduto "
                   + "LEFT JOIN coroa c ON c.idcoroa = t.codcoroa AND c.codmedium = " + this.codMedium + " "
                   + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                   + compCoroa
                   + "ORDER BY tp.idtipocoroa ASC";
        
//        System.out.println(sql);
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Corôa");
            medium.addColumn("Produto");
            medium.addColumn("Quantidade");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(50);

            while(rs.next()){
                String coroa = rs.getString("tp.nome");
                String produto = rs.getString("p.produto");
                String quant = rs.getString("t.quantidade");

                medium.addRow(new Object[]{coroa, produto, quant});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de Trabalhos. Mensagem: " + ex.getMessage());
        }       
        
    }
    
    public int retornaIdTrabalho(){
        config = new Configuracoes();
        
        String sql = "SELECT t.idtrabalho "
                   + "FROM trabsaidas t "
                   + "LEFT JOIN produtos p ON p.idproduto = t.codproduto "
                   + "LEFT JOIN coroa c ON c.idcoroa = t.codcoroa AND c.codmedium = " + this.codMedium + " "
                   + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                   + "WHERE t.codcoroa = " + this.codcoroa + " "
                   + "AND t.quantidade = " + this.quantidade + " "
                   + "AND t.codproduto = " + this.codproduto + " "
                   + "ORDER BY tp.idtipocoroa ASC";
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("t.idtrabalho");
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de Trabalhos. Mensagem: " + ex.getMessage());
        }       
        
        return 0;
    }
    
}
