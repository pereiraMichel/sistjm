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
public class TrabAtend {
    private int idtrabalho;
    private int quantidade;
    private int codproduto;
    private String protocolo;
    private String dataTrabalho;
    private String confirma;
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public String getDataTrabalho() {
        return dataTrabalho;
    }

    public void setDataTrabalho(String dataTrabalho) {
        config = new Configuracoes();
        this.dataTrabalho = config.retornaFormatoDataSQL(dataTrabalho);
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

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
    
    public void preencheTabTrabalhosCoroa(JTable tabela){
        String sql = "SELECT *, DATE_FORMAT(dataTrabalho, '%d/%m/%Y') AS dtFormat "
                + "FROM trabalhos t ";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            config = new Configuracoes();
                
            DefaultTableModel agendamento = new DefaultTableModel();
            tabela.setModel(agendamento);

            agendamento.addColumn("ID");
            agendamento.addColumn("Código");
            agendamento.addColumn("Data");
            agendamento.addColumn("Consultor");
            agendamento.addColumn("Consultado");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);

            while(rs.next()){
                int idAgendamento = rs.getInt("idagendamento");
                String codigo = rs.getString("codigo");
                String data = rs.getString("dataFormatada");
                String consultor = rs.getString("consultor");
                String consultado = rs.getString("consultado");
                String medium = rs.getString("tconsultado");

                agendamento.addRow(new Object[]{idAgendamento, codigo, data, consultor, consultado, medium});

            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agendamento. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT * FROM trabatend "
                + "WHERE "
                + "protocolo = '" + this.protocolo + "' ";
        
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
            config.gravaErroLog("Houve um erro na verificação de duplicidade do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Verificação do trabalho de atendimento", "sistejm.vertrabatend");
        }
        return false;
    }
    
    public boolean incluirTrabalhoAtendimento(){
        con = new Conexao();
        config = new Configuracoes();
        
        if(!this.verificaExistente()){
            
            this.idtrabalho = con.ultimoId("trabatend", "idtrabalho");

            String sql = "INSERT INTO trabatend (idtrabalho, quantidade, codproduto, protocolo, "
                    + "dataTrabalho, confirma) "
                    + "VALUES "
                    + "(" + this.idtrabalho + ", " + this.quantidade + ", " + this.codproduto + ", "
                    + "'" + this.protocolo + "', '" + this.dataTrabalho + "'" + ", '" + this.confirma + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro na inclusão do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Inclusão do trabalho de atendimento", "sistejm.inctrabatend");
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
                + "protocolo = '" + this.protocolo + "', "
                + "dataTrabalho = '" + this.dataTrabalho + "'"
                + "confirma = '" + this.confirma + "'"
                + " WHERE idtrabalho = " + this.idtrabalho;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na alteração do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Alteração do trabalho de atendimento", "sistejm.altrabatend");
        }
        return false;
    }
    public boolean confirmaTrabalhoAtendimento(){
        config = new Configuracoes();
    
        String sql = "UPDATE agendamento SET "
                + "confirma = '" + this.confirma + "'"
                + " WHERE idtrabalho = " + this.idtrabalho;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na alteração do trabalho de atendimento. Verifique sob o erro " + ex.getMessage(), "Alteração do trabalho de atendimento", "sistejm.altrabatend");
        }
        return false;
    }
    public boolean excluirAgendamento(){
        config = new Configuracoes();
        String sql = "DELETE FROM trabatend WHERE idtrabalho = " + this.idtrabalho;
        
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
