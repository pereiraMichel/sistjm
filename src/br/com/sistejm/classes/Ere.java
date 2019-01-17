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
public class Ere {

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    
    private int idEre;
    private String nome;

    public int getIdEre() {
        return idEre;
    }

    public void setIdEre(int idEre) {
        this.idEre = idEre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void preencheTabEres(JTable tabela){

        String sql = "SELECT * FROM ere";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel ere = new DefaultTableModel();
            tabela.setModel(ere);

//            ere.addColumn("ID");
            ere.addColumn("Erê");

//            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
//                int idEvento = rs.getInt("idere");
                String nomeEvento = rs.getString("nome");
                ere.addRow(new Object[]{nomeEvento});
//                ere.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de erês. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabEres(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM ere WHERE nome LIKE '%" + texto.getText() + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("Erê");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de erês. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT nome FROM ere WHERE nome = '" + this.nome + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("SQL: " + sql);
            
            rs.next();
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            System.out.println("Catch verificação de duplicidade de Erês ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluirEre(){
        con = new Conexao();
        if(!this.verificaExistente()){
            
            this.idEre = con.ultimoId("ere", "idere");

            String sql = "INSERT INTO ere (idere, nome) "
                    + "VALUES (" + this.idEre+ ", '" + this.nome + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                System.out.println("Catch inclusão de Erê ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarEre(){
        String sql = "UPDATE ere SET nome = '" + this.nome + "' WHERE idere = " + this.idEre;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de Erês ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirEre(){
        String sql = "DELETE FROM ere WHERE idere = " + this.idEre;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Eres ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public int retornaIdEre(){
        config = new Configuracoes();
        String sql = "SELECT * FROM ere WHERE nome = '" + this.nome + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idere");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Retorna ID Erê", "sistejm.idere");
        }
        return 0;
    }

    
}
