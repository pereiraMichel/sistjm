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
public class Entidade {
    
    private int idEntidade;
    private String nome;

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    
    
    public int getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(int idEntidade) {
        this.idEntidade = idEntidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void preencheTabEntidade(JTable tabela){

        String sql = "SELECT * FROM entidade "
                + "ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

//            orixa.addColumn("ID");
            orixa.addColumn("Entidade");

//            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
//                int idEvento = rs.getInt("identidade");
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
//                orixa.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de entidade. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabEntidade(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM entidade WHERE nome LIKE '%" + texto.getText() + "' "
                + "  ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

//            orixa.addColumn("ID");
            orixa.addColumn("Entidade");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);
//            tabela.getColumnModel().getColumn(1).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de busca da entidade. Erro: " + ex.getMessage(), "Entidade", "sistejm.entidade");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT nome FROM entidade WHERE nome = '" + this.nome + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("SQL: " + sql);
            
            //rs.next();
            if(rs.absolute(1)){
                alterarEntidade();
            }else{
                incluirEntidade();
            }
            return true;
           
        }catch(Exception ex){
            System.out.println("Catch verificação de duplicidade de Entidades ativado. Erro: " + ex.getMessage());
        }
        return false;
    }    
    public boolean incluirEntidade(){
        con = new Conexao();
        config = new Configuracoes();

//        if(!this.verificaExistente()){
        
            this.idEntidade = con.ultimoId("entidade", "identidade");

            String sql = "INSERT INTO entidade (identidade, nome) "
                    + "VALUES (" + this.idEntidade + ", '" + this.nome + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Erro na inclusão. Mensagem: " + ex.getMessage(), "Inclusão de Entidades", "sistejm.incentidades");
            }
//        }
        return false;
    }
    
    public boolean alterarEntidade(){
        config = new Configuracoes();
        
        String sql = "UPDATE entidade SET nome = '" + this.nome + "' WHERE identidade = " + this.idEntidade;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Erro na alteração. Mensagem: " + ex.getMessage(), "Alteração de Entidades", "sistejm.altentidades");
        }
        return false;
    }
    public boolean excluirEntidade(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM entidade WHERE identidade = " + this.idEntidade;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Erro na exclusão. Mensagem: " + ex.getMessage(), "Exclusão de Entidades", "sistejm.excentidades");
        }
        return false;
    }    
    public int retornaIdEntidade(){
        config = new Configuracoes();
        String sql = "SELECT * FROM entidade WHERE nome = '" + this.nome + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("identidade");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Retorna ID Entidade", "sistejm.identidade");
        }
        return 0;
    }
    
    
}
