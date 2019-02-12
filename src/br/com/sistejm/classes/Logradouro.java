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
import javax.swing.JTextField;

/**
 *
 * @author Michel
 */
public class Logradouro {
    
    private int idLogradouro;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public int getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(int idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public boolean verificaEndereco(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT * FROM logradouro WHERE cod_medium = " + this.codMedium;
        
//        System.out.println(sql);
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                
                if(rs.absolute(1)){
                    alteraEndereco();
                }else{
                    incluirEndereco();
                }
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro de verificação de Endereço. Erro: " + ex.getMessage(), "Verificação do Endereço", "sistejm.incluiendereco");
            }
            return false;
    }
    public boolean incluirEndereco(){
        config = new Configuracoes();
        con = new Conexao();
            
            this.idLogradouro = con.ultimoId("logradouro", "idlogradouro");

            String sql = "INSERT INTO logradouro (idlogradouro, endereco, bairro, cidade, estado, cod_medium) "
                    + "VALUES (" + this.idLogradouro + ", '" + this.endereco + "', '" + this.bairro + "', "
                    + "'" + this.cidade + "', '" + this.estado + "', " + this.codMedium + ")";
            
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);
                
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro no Endereço. Erro: " + ex.getMessage(), "Inclusão do Endereço", "sistejm.incluiendereco");
            }
        return false;
    }
    public boolean alteraEndereco(){
        config = new Configuracoes();
        con = new Conexao();

            String sql = "UPDATE logradouro SET endereco = '" + this.endereco + "', bairro = '" + this.bairro + "',"
                    + " cidade = '" + this.cidade + "', estado = '" + this.estado + "' "
                    + "WHERE cod_medium = " + this.codMedium;
            
//        System.out.println(sql);
            
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                config.gravaBDBackup(sql);
                
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro no Endereço. Erro: " + ex.getMessage(), "Alteração do Endereço", "sistejm.alteraendereco");
            }
        return false;
    }
    
    public boolean excluirEndereco(){
        config = new Configuracoes();
        String sql = "DELETE FROM logradouro WHERE cod_medium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no Endereço. Erro: " + ex.getMessage(), "Exclusão do Endereço", "sistejm.excluiendereco");
        }
        return false;
    }

    public int retornaIdLogradouro(String endereco){
        config = new Configuracoes();
        con = new Conexao();
        String sql = "SELECT * FROM logradouro WHERE endereco = '" + endereco + " "
                + "AND cod_medium = " + this.codMedium;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idlogradouro");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no Endereço. Erro: " + ex.getMessage(), "Retorno ID do Endereço", "sistejm.endereco");
        }
        return 0;
    }
    
    public void exibeEnderecoMedium(JTextField idEndereco, JTextField endereco, JTextField bairro, JTextField cidade, JTextField estado){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT * FROM logradouro WHERE cod_medium = " + this.codMedium;
        
        System.out.println(sql);
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                
                if(rs.absolute(1)){
                    idEndereco.setText(rs.getString("idlogradouro"));
                    endereco.setText(rs.getString("endereco"));
                    bairro.setText(rs.getString("bairro"));
                    cidade.setText(rs.getString("cidade"));
                    estado.setText(rs.getString("estado"));
                }

            }catch(Exception ex){
                config.gravaErroLog("Houve um erro de verificação de Endereço do médium. Erro: " + ex.getMessage(), "Verificação do Endereço", "sistejm.verificaendereco");
            }
    }
}
