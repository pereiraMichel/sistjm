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

/**
 *
 * @author Michel
 */
public class TrabalhoSaidas {
    
    private int idtrabalho;
    private int quantidade;
    private int codproduto;
    private int codcoroa;
    private String dataTrabalho;
    private String confirma;
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

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

    public String getDataTrabalho() {
        return dataTrabalho;
    }

    public void setDataTrabalho(String dataTrabalho) {
        config = new Configuracoes();
        this.dataTrabalho = config.retornaFormatoDataSQL(dataTrabalho);
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public Configuracoes getConfig() {
        return config;
    }

    public void setConfig(Configuracoes config) {
        this.config = config;
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT * FROM trabsaidas "
                + "WHERE "
                + "codcoroa = " + this.codcoroa;
        
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
        
        if(!this.verificaExistente()){
            
            this.idtrabalho = con.ultimoId("trabsaidas", "idtrabalho");

            String sql = "INSERT INTO trabsaidas (idtrabalho, quantidade, codproduto, codcoroa) "
                    + "VALUES "
                    + "(" + this.idtrabalho + ", " + this.quantidade + ", " + this.codproduto + ", "
                    + "'" + this.codcoroa + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro na inclusão do trabalho de saídas. Verifique sob o erro " + ex.getMessage(), "Inclusão do trabalho de saídas", "sistejm.inctrabsaida");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Trabalho já existente.");
        }
        return false;
    }
    
    public boolean alterarTrabalhoAtendimento(){
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
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na alteração do trabalho de saídas. Verifique sob o erro " + 
                    ex.getMessage(), "Alteração do trabalho de saídas", "sistejm.altrabatend");
        }
        return false;
    }

    public boolean excluirSaida(){
        config = new Configuracoes();
        String sql = "DELETE FROM trabsaidas WHERE idtrabalho = " + this.idtrabalho;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na exclusão do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Exclusão do trabalho de atendimento", "sistejm.excrabatend");
        }
        return false;
    }
    
}
