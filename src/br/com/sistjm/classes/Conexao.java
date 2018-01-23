/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistjm.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class Conexao {
    
    private String servidor;
    private String usuario;
    private String senha;
    private String database;
//    private String driver;
    private String caminho;
  

    public static Properties getProp() throws IOException {//br\com\satoru\bundles
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("banco.properties");
        props.load(file);
        return props;
    }    
    
    
    public Connection getConnection() throws IOException{

        Connection con = null;
        Properties prop = getProp();

        this.servidor = prop.getProperty("prop.server.servidor");
        this.usuario = prop.getProperty("prop.server.usuario");
        this.senha = prop.getProperty("prop.server.senha");
        this.database = prop.getProperty("prop.server.database");
        this.caminho = "jdbc:mysql://";
//        this.driver = "com.mysql.jdbc.Driver";
        
        try {
            con = DriverManager.getConnection(this.caminho + this.servidor + "/" + this.database, this.usuario, this.senha);

            if (con == null) {
                JOptionPane.showMessageDialog(null, "Houve algum erro de conexão. Consulte o código.");
            }

            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco não conectado! Consulte as informações no código.");
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
            return null;
        }
    }
    
    public void fechaConexao() throws IOException, SQLException{
        this.getConnection().close();
    }

    
}
