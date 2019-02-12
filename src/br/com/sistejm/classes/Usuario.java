/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import static br.com.sistejm.classes.Conexao.getProp;
import br.com.sistejm.telas.DPrimeiroUsuario;
import br.com.sistejm.telas.TelaInicial;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.telas.BancoDados;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class Usuario {
    
    private int idUsuario;
    private String nome;
    private String senha;
    private String email;
    private String dataCadastro;
    private String dataAlteracao;
    private int codAcesso;
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Criptografia cript;
    TelaInicial inicial;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        cript = new Criptografia();
        cript.setSenha(senha);
        this.senha = cript.md5();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public int getCodAcesso() {
        return codAcesso;
    }

    public void setCodAcesso(int codAcesso) {
        this.codAcesso = codAcesso;
    }
    
    public boolean validaUsuarioDuplicidade(String email, String nome){
        String sql = "SELECT nome, email FROM tblusuario "
                + "WHERE nome = '" + nome + "' AND email = '" + email + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return true;
            }
            
            rs.close();
            stmt.close();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            //return false;
        }
            return false;
    }
    
    public int consultaLoginUsuario(){
        con = new Conexao();
        inicial = new TelaInicial();
        String sql = "SELECT id_usuario, nome, senha FROM tblusuario "
                + "WHERE "
                + "nome = '" + this.nome + "' "
                + "AND senha = '" + this.senha + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("id_usuario");
            }
//            rs.close();
//            stmt.close();
            return 0;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }        
            return 0;
    }
    
    public boolean verificaPrimeiroUsuario(){
        Configuracoes config = new Configuracoes();
        TelaInicial inicial = new TelaInicial();
        DPrimeiroUsuario primeiro = new DPrimeiroUsuario(inicial, false);
        try{
            String sql = "SELECT COUNT(id_usuario) AS total FROM tblusuario";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            rs.next();
            //System.out.println("Total Banco: " + rs.getString("total"));
           
            if(rs.getInt("total")> 0){
                config.gravaAcessoUsuario(rs.getInt("total"), 61315);//true
            }else{
                config.gravaAcessoUsuario(rs.getInt("total"), 29350);//false
                primeiro.setLocationRelativeTo(primeiro);
                primeiro.setTitle("Primeiro Usuário");
                primeiro.setVisible(true);
            }
           
           return true;
            
        } catch(Exception ex){
            System.out.println("Houve um erro na tentativa de conexão. Descrição: " + ex.getMessage());
            return false;
        }
        
    }
    
    public void incluirUsuario(){
        
        if(validaUsuarioDuplicidade(this.email, this.nome)){
            JOptionPane.showMessageDialog(null, "Usuário existente", "ERRO", JOptionPane.ERROR);
        }else{
            con = new Conexao();
            this.idUsuario = con.ultimoId("tblusuario", "id_usuario");

            String sql = "INSERT INTO tblusuario"
                    + " (id_usuario, nome, senha, email, dataCadastro, dataAlteracao, codAcesso)"
                    + " VALUES (" + this.idUsuario + ", '" + this.nome + "', '" + this.senha +"', '" + 
                    this.email + "', '" + this.dataCadastro + "', '" + 
                    this.dataAlteracao + "', " + this.codAcesso + ")";
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.execute(sql);

                config.gravaAcessoUsuario(this.idUsuario, 61315);

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public boolean verificaAcessoAtivo() throws IOException{
        String caminhoUser = "C:\\sistejm\\config\\datauser.properties";

        File arquivoExistente = new File(caminhoUser);
        
        try{
        
            if(arquivoExistente.exists()){
                FileInputStream file = new FileInputStream(arquivoExistente);
                Properties propUser = new Properties();
                propUser.load(file);
                file.close();

                String autoriza = propUser.getProperty("prop.user.autorizacao");
                if(autoriza.equals("29350")){
                    return true;
                }
            }
        }catch(Exception ex){
            System.out.println("Catch ativado: " + ex.getMessage());
            return false;
        }
        return false;

    }
    
    public String retornaUsuario(){
        con = new Conexao();
        config = new Configuracoes();
        String sql = "SELECT nome FROM tblusuario "
                + "WHERE "
                + "id_usuario = " + this.idUsuario;
        
//        System.out.println(sql);
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("nome");
            }
        }catch(Exception ex){
            config.gravaErroLog("Erro no retorno do nome de usuário. " + ex.getMessage(), "Retorna Nome de Usuário" , "sistejm.retnomuser");
        }        
            return null;
    }
    public String retornaAcesso(){
        con = new Conexao();
        config = new Configuracoes();
            String sql = "SELECT t.id_usuario, a.acesso FROM tblusuario t "
                    + "INNER JOIN acesso a ON t.codAcesso = a.idacesso "
                    + "WHERE t.id_usuario = " + this.idUsuario;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("acesso");
            }
        }catch(Exception ex){
            config.gravaErroLog("Erro no retorno do acesso de usuário. " + ex.getMessage(), "Retorna Acesso de Usuário" , "sistejm.retnomuser");
        }        
            return null;
    }
        
}
