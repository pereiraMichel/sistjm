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
public class Produtos {
    
    private int idProduto;
    private String produto;
    private int codUsuario;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public boolean verificaDuplicidade(){
        
        String sql = "SELECT * FROM produtos WHERE produto = '" + this.produto + "'";
        
        con = new Conexao();
        config = new Configuracoes();
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                alterarProduto();
                return false;
            }
            incluirProduto();

        }catch(Exception ex){
            config.gravaErroLog("Problemas na verificação de duplicidade de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Problemas na verificação de duplicidade do produto. Erro: " + ex.getMessage());
        }        
            return true;
    }
    
    public boolean incluirProduto(){
        con = new Conexao();
        config = new Configuracoes();
        
        this.idProduto = con.ultimoId("produtos", "idproduto");
        
        String sql = "INSERT INTO produtos (idproduto, produto, codusuario) "
                + "VALUES (" + this.idProduto + ", " + "'" + this.produto + "', " + this.codUsuario + ")";
        
//        System.out.println(sql);
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog("Problemas na inclusão de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Problemas na inclusão do produto. Erro: " + ex.getMessage());
            return false;
        }
        //return false;
    }
    
    public boolean alterarProduto(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "UPDATE produtos "
                + "SET produto = '" + this.produto + "', codUsuario = " + this.codUsuario + " "
                + "WHERE idproduto = " + this.idProduto;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog("Problemas na alteração de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Problemas na alteração do produto. Erro: " + ex.getMessage());
            return false;
        }        
    }
    
    public boolean excluirProduto(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "DELETE FROM produtos "
                + "WHERE idproduto = " + this.idProduto;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog("Problemas na exclusão de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Problemas na exclusão do evento. Erro: " + ex.getMessage());
            return false;
        }    
    }
    
    public void preencheTabelaProdutos(JTable produto){
        String sql = "SELECT * FROM produtos";
        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
//            if(rs.absolute(1)){
                
                DefaultTableModel tabela = new DefaultTableModel();
                produto.setModel(tabela);
                
//                tabela.addColumn("ID");
                tabela.addColumn("Produto");
                
//                produto.getColumnModel().getColumn(0).setPreferredWidth(30);
                produto.getColumnModel().getColumn(0).setPreferredWidth(100);
                
                while(rs.next()){
//                    int idEvento = rs.getInt("idproduto");
                    String nomeEvento = rs.getString("produto");
                    tabela.addRow(new Object[]{nomeEvento});
                }
                
//            }
            
        }catch(Exception ex){
            config.gravaErroLog("Problemas na tabela de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Erro em tabela de produtos. Mensagem: " + ex.getMessage());
        }
    }
    
    public void pesquisaCampoTabela(JTable produto, JTextField campo){
        String sql = "SELECT * FROM produtos WHERE produto LIKE '%" + campo.getText() + "%'";
        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
                DefaultTableModel tabela = new DefaultTableModel();
                produto.setModel(tabela);
                
                tabela.addColumn("ID");
                tabela.addColumn("Produto");
                
                produto.getColumnModel().getColumn(0).setPreferredWidth(30);
                produto.getColumnModel().getColumn(1).setPreferredWidth(100);
                
                while(rs.next()){
                    int idEvento = rs.getInt("idproduto");
                    String nomeEvento = rs.getString("produto");
                    tabela.addRow(new Object[]{idEvento, nomeEvento});
                }
                
//            }
            
        }catch(Exception ex){
            config.gravaErroLog("Problemas na busca da tabela de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
//            System.out.println("Erro em tabela de produtos. Mensagem: " + ex.getMessage());
        }        
    }
    
    public int retornaIdProduto(String nomeProduto){
        String sql = "SELECT * FROM produtos WHERE produto = '" + nomeProduto + "'";
        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                int id = rs.getInt("idproduto");
                return id;
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Problemas na busca da tabela de produtos. Erro: " + ex.getMessage() + "\nSQL: " + sql, "Produtos", "sistejm.produtos");
        }        
        return 0;
    }
    
}
